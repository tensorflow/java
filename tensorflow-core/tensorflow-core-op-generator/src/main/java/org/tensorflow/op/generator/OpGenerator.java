/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==============================================================================
 */
package org.tensorflow.op.generator;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_ApiDefMapGet;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_ApiDefMapPut;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewApiDefMap;

import com.google.protobuf.InvalidProtocolBufferException;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.TensorFlow;
import org.tensorflow.internal.c_api.TF_ApiDefMap;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.proto.framework.ApiDef;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpList;

public final class OpGenerator {

  private static final String license =
      "/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.\n" +
          "\n" +
          "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
          "you may not use this file except in compliance with the License.\n" +
          "You may obtain a copy of the License at\n" +
          "\n" +
          "    http://www.apache.org/licenses/LICENSE-2.0\n" +
          "\n" +
          "Unless required by applicable law or agreed to in writing, software\n" +
          "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
          "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
          "See the License for the specific language governing permissions and\n" +
          "limitations under the License.\n" +
          "=======================================================================*/" +
          "\n";

  /**
   * Args should be {@code <outputDir> [--base_package base_package] [apiDefDir1] [apiDefDir2] [...]}.
   *
   * {@code base_package} is {@code org.tensorflow.op} by default.
   * <p>
   * <b>Will delete everything in {@code outputDir}.</b>
   */
  public static void main(String[] args) {

    if (args.length < 1 || args[0].equals("--help")) {
      System.out.println("Args should be: <outputDir> [--base_package base_package] [apiDefDir1] [apiDefDir2] [...]");
      return;
    }

    File outputDir = new File(args[0]);
    ArrayList<String> apiDirs = new ArrayList<>(args.length);
    String packageName = "org.tensorflow.op";

    for (int i = 1; i < args.length; i++) {
      if (args[i].equals("--base_package")) {
        packageName = args[++i];
        continue;
      }
      apiDirs.add(args[i]);
    }

    File basePackage = new File(outputDir, packageName.replace('.', '/'));

    if(basePackage.exists()){
      try {
        Files.walkFileTree(basePackage.toPath(), new SimpleFileVisitor<Path>() {
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
          }
        });
      } catch (IOException ignored) {

      }
    }

    generate(outputDir, packageName, apiDirs);
  }

  /**
   * Build the list of ops and api defs, then call {@link #generate(File, String, Map)}
   */
  private static void generate(File outputDir, String packageName, ArrayList<String> apiDirs) {
    OpList opList = TensorFlow.registeredOpList();
    Map<OpDef, ApiDef> defs = new LinkedHashMap<>(opList.getOpCount());
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_ApiDefMap apiDefs = TF_NewApiDefMap(TF_Buffer.newBufferFromString(opList), status);
      status.throwExceptionIfNotOK();

      apiDirs.forEach(dirName -> {
        File dir = new File(dirName);
        if (dir.exists()) {
          opList.getOpList().forEach(op -> {
            File def = new File(dir, "api_def_" + op.getName() + ".pbtxt");
            if (def.exists()) {
              try (PointerScope innerScope = new PointerScope()) {
                String text = Files.readString(def.toPath());
                TF_Status innerStatus = TF_Status.newStatus();
                TF_ApiDefMapPut(apiDefs, text, text.length(), innerStatus);
                innerStatus.throwExceptionIfNotOK();
              } catch (IOException e) {
                throw new IllegalStateException("Failed to read file " + def, e);
              }
            }
          });
        }
      });

      opList.getOpList().forEach(op -> {
        try (PointerScope innerScope = new PointerScope()) {
          TF_Status innerStatus = TF_Status.newStatus();
          TF_Buffer buffer = TF_ApiDefMapGet(apiDefs, op.getName(), op.getName().length(), innerStatus);
          innerStatus.throwExceptionIfNotOK();
          defs.put(op, ApiDef.parseFrom(buffer.dataAsByteBuffer()));
        } catch (InvalidProtocolBufferException e) {
          throw new IllegalStateException("Failed to parse ApiDef for " + op.getName(), e);
        }
      });

    }

    generate(outputDir, packageName, defs);
  }

  /**
   * Generate all the ops that pass {@link ClassGenerator#canGenerateOp(OpDef, ApiDef)}.
   */
  private static void generate(File outputDir, String basePackage, Map<OpDef, ApiDef> ops) {
    ops.entrySet().stream().filter(e -> ClassGenerator.canGenerateOp(e.getKey(), e.getValue())).forEach((entry) -> {
      entry.getValue().getEndpointList().forEach((endpoint) -> {

        String name;
        String pack;

        int pos = endpoint.getName().lastIndexOf('.');
        if (pos > -1) {
          pack = endpoint.getName().substring(0, pos);
          name = endpoint.getName().substring(pos + 1);
        } else {
          pack = "core";
          name = endpoint.getName();
        }

        TypeSpec.Builder cls = TypeSpec.classBuilder(name);
        try {
          new ClassGenerator(
              cls,
              entry.getKey(),
              entry.getValue(),
              basePackage,
              basePackage + "." + pack,
              pack,
              name,
              endpoint).buildClass();
        } catch (Exception e) {
          throw new IllegalStateException("Failed to generate class for op " + entry.getKey().getName(), e);
        }
        TypeSpec spec = cls.build();

        JavaFile file = JavaFile.builder(basePackage + "." + pack, spec)
            .indent("  ")
            .skipJavaLangImports(true)
            .build();

        File outputFile = new File(outputDir, basePackage.replace('.', '/') +
            '/' + pack.replace('.', '/') + '/' + spec.name + ".java");
        outputFile.getParentFile().mkdirs();
        try {
          StringBuilder builder = new StringBuilder();
          builder.append(license + '\n');
          builder.append("// This class has been generated, DO NOT EDIT!\n\n");
          file.writeTo(builder);

          Files.writeString(outputFile.toPath(), builder.toString(), StandardOpenOption.WRITE,
              StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ioException) {
          throw new IllegalStateException("Failed to write file " + outputFile, ioException);
        }
      });
    });
  }


}
