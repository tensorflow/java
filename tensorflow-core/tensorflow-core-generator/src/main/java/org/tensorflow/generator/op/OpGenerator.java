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
package org.tensorflow.generator.op;

import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
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

import org.bytedeco.javacpp.BytePointer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.tensorflow.internal.c_api.TF_ApiDefMap;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.global.tensorflow;
import org.tensorflow.proto.ApiDef;
import org.tensorflow.proto.OpDef;
import org.tensorflow.proto.OpList;

public final class OpGenerator {

  private static final String LICENSE =
      "/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.\n"
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

  private static final String HELP_TEXT = "Args should be: [-p <base_package>] <outputDir> [<opDefFile>]";

  private static final String DEFAULT_OP_DEF_FILE = "org/tensorflow/ops.pbtxt";

  /**
   * Args should be {@code <outputDir> <opDefFile> [base_package]}.
   *
   * <p>{@code base_package} is {@code org.tensorflow.op} by default.
   *
   * <p><b>Will delete everything in {@code outputDir}.</b>
   */
  public static void main(String[] args) throws IOException, URISyntaxException {
    //TF_RuntimeLibrary.load();

    int argIdx = 0;

    if (arg(args, argIdx).equals("--help")) {
      System.out.println(HELP_TEXT);
      return;
    }

    String packageName = "org.tensorflow.op";

    if (arg(args, argIdx).equals("-p")) {
      packageName = arg(args, ++argIdx);
      ++argIdx;
    }

    File outputDir = new File(arg(args, argIdx++));
    OpList opList = null;

    if (argIdx < args.length - 1) {
      var opDefsFile = new File(arg(args, argIdx++));

      if (!opDefsFile.exists()) {
        System.err.println("Op def file " + opDefsFile + " does not exist.");
        System.exit(1);
      }
      if (!opDefsFile.isFile()) {
        System.err.println("Op def file " + opDefsFile + " is not a file.");
        System.exit(1);
      }
      if (!opDefsFile.canRead()) {
        System.err.println("Can't read Op def file " + opDefsFile + ".");
        System.exit(1);
      }
      try (var opDefsInput = new FileInputStream(opDefsFile)) {
        opList = readOpList(opDefsFile.getName(), opDefsInput);
      }
    } else {
      var opDefsFile = OpGenerator.class.getClassLoader().getResource(DEFAULT_OP_DEF_FILE);

      if (opDefsFile == null) {
        throw new FileNotFoundException("\"" + DEFAULT_OP_DEF_FILE + "\" cannot be found in native artifact");
      }
      try (var opDefsInput = opDefsFile.openStream()) {
        opList = readOpList(opDefsFile.getFile(), opDefsInput);
      }
    }

    File basePackage = new File(outputDir, packageName.replace('.', '/'));

    if (basePackage.exists()) {
      try {
        Files.walkFileTree(
            basePackage.toPath(),
            new SimpleFileVisitor<>() {
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
    generate(outputDir, packageName, opList);
  }

  private static String arg(String[] args, int idx) {
    if (idx >= args.length) {
      System.err.println(HELP_TEXT);
      System.exit(1);
    }
    return args[idx];
  }

  private static OpList readOpList(String filename, InputStream protoInput) {
    try {
      if (filename.endsWith(".pbtxt")) {
        return TextFormat.parse(new String(protoInput.readAllBytes()), OpList.class);
      }
      return OpList.parseFrom(protoInput);

    } catch (IOException e) {
      throw new RuntimeException("Error parsing proto file named \"" + filename + "\"", e);
    }
  }

  private static Map<OpDef, ApiDef> buildDefMap(OpList opList) {
    try (TF_Status status = TF_Status.newStatus();
         TF_ApiDefMap apiDefMap = tensorflow.TF_NewApiDefMap(TF_Buffer.newBufferFromString(opList), status)){
      status.throwExceptionIfNotOK();

      // We must build the ApiDefMap before any attempt to retrieve a value from it, or it will fail
      mergeApiDefs(apiDefMap, "org/tensorflow/base_api", status);
      mergeApiDefs(apiDefMap, "org/tensorflow/java_api", status);

      Map<OpDef, ApiDef> defs = new LinkedHashMap<>();
      for (OpDef opDef : opList.getOpList()) {
        var apiDef = tensorflow.TF_ApiDefMapGet(apiDefMap, opDef.getName(), opDef.getName().length(), status);
        defs.put(opDef, ApiDef.parseFrom(apiDef.copyData()));
      }
      return defs;

    } catch (Exception e) {
      throw e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
    }
  }

  private static void mergeApiDefs(TF_ApiDefMap apiDefMap, String apiDefsResourceFolder, TF_Status status) {
    try {
      var resourceResolver = new PathMatchingResourcePatternResolver(OpGenerator.class.getClassLoader());
      var apiDefs = resourceResolver.getResources(apiDefsResourceFolder + "/api_def_*.pbtxt");
      for (var apiDef : apiDefs) {
        try (var apiDefInput = apiDef.getInputStream()) {
          tensorflow.TF_ApiDefMapPut(apiDefMap, new BytePointer(apiDefInput.readAllBytes()), apiDef.contentLength(), status);
          status.throwExceptionIfNotOK();
        } catch (IOException e) {
          throw new RuntimeException("Failed to parse API definition in resource \"" + apiDef.getURI() + "\"", e);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to browse API definitions in resource folder \"" + apiDefsResourceFolder + "\"", e);
    }
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
  private static void generate(File outputDir, String basePackage, OpList opList) {
    Map<OpDef, ApiDef> ops = buildDefMap(opList);

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
