/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow.op.generator;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSet;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.tensorflow.proto.framework.ApiDef;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpList;

public final class OpGenerator {

  private static final String LICENSE =
      "/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.\n"
          + "\n"
          + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
          + "you may not use this file except in compliance with the License.\n"
          + "You may obtain a copy of the License at\n"
          + "\n"
          + "    http://www.apache.org/licenses/LICENSE-2.0\n"
          + "\n"
          + "Unless required by applicable law or agreed to in writing, software\n"
          + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
          + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
          + "See the License for the specific language governing permissions and\n"
          + "limitations under the License.\n"
          + "=======================================================================*/"
          + "\n";

  private static final String HELP_TEXT = "Args should be: <outputDir> <opDefFile> [base_package]";

  private static final int API_DEF_FIELD_NUMBER = 100;

  /**
   * Args should be {@code <outputDir> <opDefFile> [base_package]}.
   *
   * <p>{@code base_package} is {@code org.tensorflow.op} by default.
   *
   * <p><b>Will delete everything in {@code outputDir}.</b>
   */
  public static void main(String[] args) {

    if (args[0].equals("--help")) {
      System.out.println(HELP_TEXT);
      return;
    }

    if (args.length < 2) {
      System.err.println(HELP_TEXT);
      System.exit(1);
    }

    File outputDir = new File(args[0]);
    File inputFile = new File(args[1]);

    if (!inputFile.exists()) {
      System.err.println("Op def file " + inputFile + " does not exist.");
      System.exit(1);
    }

    if (!inputFile.isFile()) {
      System.err.println("Op def file " + inputFile + " is not a file.");
      System.exit(1);
    }

    if (!inputFile.canRead()) {
      System.err.println("Can't read Op def file " + inputFile + ".");
      System.exit(1);
    }

    String packageName = "org.tensorflow.op";

    if (args.length > 2) {
      packageName = args[2];
    }

    File basePackage = new File(outputDir, packageName.replace('.', '/'));

    if (basePackage.exists()) {
      try {
        Files.walkFileTree(
            basePackage.toPath(),
            new SimpleFileVisitor<Path>() {
              @Override
              public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                  throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
              }

              @Override
              public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                  throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
              }
            });
      } catch (IOException ignored) {

      }
    }

    generate(outputDir, packageName, inputFile);
  }

  /** Build the list of ops and api defs, then call {@link #generate(File, String, Map)} */
  private static void generate(File outputDir, String packageName, File opDefs) {
    OpList opList = null;
    try {
      opList = OpList.parseFrom(new FileInputStream(opDefs));
    } catch (FileNotFoundException e) {
      // already checked in main, shouldn't happen here
      System.err.println("Op def file " + opDefs + " not found.");
      System.exit(1);
    } catch (IOException e) {
      throw new RuntimeException(
          "Error parsing op def file "
              + opDefs
              + ", was it generated by op_export_main.cc (in tensorflow-core-api)?",
          e);
    }
    Map<OpDef, ApiDef> defs = new LinkedHashMap<>(opList.getOpCount());

    for (OpDef op : opList.getOpList()) {
      try {
        if (!op.getUnknownFields().hasField(API_DEF_FIELD_NUMBER)) {
          throw new RuntimeException(
              "No attached ApiDef for op "
                  + op.getName()
                  + ", was "
                  + opDefs
                  + " generated by op_export_main.cc (in tensorflow-core-api)?  The op's ApiDef should be"
                  + " attached as unknown field "
                  + API_DEF_FIELD_NUMBER
                  + ".");
        }
        ApiDef api =
            ApiDef.parseFrom(
                op.getUnknownFields()
                    .getField(API_DEF_FIELD_NUMBER)
                    .getLengthDelimitedList()
                    .get(0));
        defs.put(
            op.toBuilder().setUnknownFields(UnknownFieldSet.newBuilder().build()).build(), api);
      } catch (InvalidProtocolBufferException e) {
        throw new RuntimeException(
            "Could not parse attached ApiDef for op "
                + op.getName()
                + ", was "
                + opDefs
                + " generated by op_export_main.cc (in tensorflow-core-api)?",
            e);
      }
    }

    generate(outputDir, packageName, defs);
  }

  private static void writeToFile(TypeSpec spec, File outputDir, String packageName) {
    JavaFile file =
        JavaFile.builder(packageName, spec).indent("  ").skipJavaLangImports(true).build();

    File outputFile =
        new File(outputDir, packageName.replace('.', '/') + '/' + spec.name + ".java");
    outputFile.getParentFile().mkdirs();
    try {
      StringBuilder builder = new StringBuilder();
      builder.append(LICENSE + '\n');
      builder.append("// This class has been generated, DO NOT EDIT!\n\n");
      file.writeTo(builder);

      Files.write(
          outputFile.toPath(),
          builder.toString().getBytes(StandardCharsets.UTF_8),
          StandardOpenOption.WRITE,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException ioException) {
      throw new IllegalStateException("Failed to write file " + outputFile, ioException);
    }
  }

  /** Generate all the ops that pass {@link ClassGenerator#canGenerateOp(OpDef, ApiDef)}. */
  private static void generate(File outputDir, String basePackage, Map<OpDef, ApiDef> ops) {
    List<FullOpDef> fullOps =
        ops.entrySet().stream()
            .filter(e -> ClassGenerator.canGenerateOp(e.getKey(), e.getValue()))
            .flatMap(
                (entry) ->
                    entry.getValue().getEndpointList().stream()
                        .map(
                            (endpoint) -> {
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

                              return new FullOpDef(
                                  entry.getKey(),
                                  entry.getValue(),
                                  basePackage,
                                  basePackage + "." + pack,
                                  pack,
                                  name,
                                  endpoint);
                            }))
            .collect(Collectors.toList());

    List<StatefulPair> statefulPairs = StatefulPair.extractStatefulPairs(fullOps);

    fullOps.forEach(
        (def) -> {
          TypeSpec spec = def.buildOpClass();

          writeToFile(spec, outputDir, def.packageName);
        });

    statefulPairs.forEach(
        (pair) -> {
          pair.buildOpClasses()
              .forEach((spec) -> writeToFile(spec, outputDir, pair.getPackageName()));
        });
  }
}
