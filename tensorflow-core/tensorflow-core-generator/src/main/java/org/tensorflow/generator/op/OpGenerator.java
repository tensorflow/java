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

import com.google.protobuf.TextFormat;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bytedeco.javacpp.BytePointer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.tensorflow.internal.c_api.TF_ApiDefMap;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.global.tensorflow;
import org.tensorflow.proto.ApiDef;
import org.tensorflow.proto.ApiDefs;
import org.tensorflow.proto.OpDef;
import org.tensorflow.proto.OpList;

public final class OpGenerator {

  private static final String LICENSE =
      "/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.\n"
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

  private static final String HELP_TEXT =
      "Args should be: [--help] [-p <basePackage>] [-a <apiDefsPath>] [-o <outputPath>] [-c] [<opDefPath>]";

  private static final String DEFAULT_OP_DEF_FILE = "org/tensorflow/ops.pbtxt";

  private static final Scanner USER_PROMPT = new Scanner(System.in, StandardCharsets.UTF_8);

  /**
   * Args should be {@code <outputDir> <opDefFile> [base_package]}.
   *
   * <p>{@code base_package} is {@code org.tensorflow.op} by default.
   *
   * <p><b>Will delete everything in {@code outputDir}.</b>
   */
  public static void main(String[] args) throws IOException, URISyntaxException {
    String packageName = "org.tensorflow.op";
    String apiDefsPath = null;
    String outputPath = ".";
    String opDefsPath = null;
    boolean createMissingApiDefs = false;

    for (int argIdx = 0; argIdx < args.length; ++argIdx) {
      var arg = arg(args, argIdx);
      switch (arg) {
        case "--help":
        case "-h":
          System.out.println(HELP_TEXT);
          return;
        case "-p":
          packageName = arg(args, ++argIdx);
          break;
        case "-a":
          apiDefsPath = arg(args, ++argIdx);
          break;
        case "-o":
          outputPath = arg(args, ++argIdx);
          break;
        case "-c":
          createMissingApiDefs = true;
          break;
        default:
          if (argIdx == args.length - 1) {
            opDefsPath = arg(args, argIdx);
          } else {
            System.err.println("Unknown argument \"" + arg + "\"");
            System.out.println(HELP_TEXT);
            System.exit(1);
          }
      }
    }
    System.out.println("Generating ops files:");
    System.out.println("    Ops definitions: " + (opDefsPath != null ? opDefsPath : "<default>"));
    System.out.println("    Java API definitions: " + apiDefsPath);
    System.out.println("    Package name: " + packageName);
    System.out.println("    Output directory: " + outputPath);

    File outputDir = new File(outputPath);
    OpList opList = null;

    if (opDefsPath != null) {
      var opDefsFile = new File(opDefsPath);

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
        throw new FileNotFoundException(
            "\"" + DEFAULT_OP_DEF_FILE + "\" cannot be found in native artifact");
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

    var generator = new OpGenerator(packageName, apiDefsPath, outputDir, createMissingApiDefs);
    generator.generate(opList);
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
        return TextFormat.parse(
            new String(protoInput.readAllBytes(), StandardCharsets.UTF_8), OpList.class);
      }
      return OpList.parseFrom(protoInput);

    } catch (IOException e) {
      throw new RuntimeException("Error parsing proto file named \"" + filename + "\"", e);
    }
  }

  private final String basePackage;
  private final Path apiDefsPath;
  private final File outputDir;
  private final boolean createMissingApiDefs;

  private OpGenerator(
      String basePackage, String apiDefsPath, File outputDir, boolean createMissingApiDefs) {
    this.basePackage = basePackage;
    this.apiDefsPath = Path.of(apiDefsPath);
    this.outputDir = outputDir;
    this.createMissingApiDefs = createMissingApiDefs;
  }

  private Map<OpDef, ApiDef> buildDefMap(OpList opList) {
    TF_ApiDefMap apiDefMap = null;
    try (TF_Status status = TF_Status.newStatus()) {
      apiDefMap = tensorflow.TF_NewApiDefMap(TF_Buffer.newBufferFromString(opList), status);
      status.throwExceptionIfNotOK();

      // Check if there is any missing APIs in the provided path, if so give a chance to the invoker
      // of this generator
      // to create one before continuing
      for (OpDef opDef : opList.getOpList()) {
        var apiDefFile = apiDefsPath.resolve("api_def_" + opDef.getName() + ".pbtxt").toFile();
        if (!apiDefFile.exists()) {
          if (createMissingApiDefs) {
            createApiDef(opDef, apiDefFile);
          } else {
            System.out.println("Warning: No Java API definitions for operation " + opDef.getName());
          }
        }
      }

      // We must build the ApiDefMap before any attempt to retrieve a value from it, or it will fail
      // Load first the base APIs coming from the native artifact, then load provided APIs
      mergeBaseApiDefs(apiDefMap, status);
      mergeApiDefs(apiDefMap, status);

      Map<OpDef, ApiDef> defs = new LinkedHashMap<>();
      for (OpDef opDef : opList.getOpList()) {
        var apiDef =
            tensorflow.TF_ApiDefMapGet(
                apiDefMap, opDef.getName(), opDef.getName().length(), status);
        defs.put(opDef, ApiDef.parseFrom(apiDef.copyData()));
      }
      return defs;

    } catch (Exception e) {
      throw e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);

    } finally {
      if (apiDefMap != null) {
        tensorflow.TF_DeleteApiDefMap(apiDefMap);
      }
    }
  }

  private void mergeBaseApiDefs(TF_ApiDefMap apiDefMap, TF_Status status) {
    try {
      var resourceResolver =
          new PathMatchingResourcePatternResolver(OpGenerator.class.getClassLoader());
      var apiDefs = resourceResolver.getResources("org/tensorflow/base_api/api_def_*.pbtxt");
      for (var apiDef : apiDefs) {
        try (var apiDefInput = apiDef.getInputStream()) {
          tensorflow.TF_ApiDefMapPut(
              apiDefMap,
              new BytePointer(apiDefInput.readAllBytes()),
              apiDef.contentLength(),
              status);
          status.throwExceptionIfNotOK();
        } catch (IOException e) {
          throw new RuntimeException(
              "Failed to parse API definition in resource \"" + apiDef.getURI() + "\"", e);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(
          "Failed to browse API definitions in resource folder \"" + apiDefsPath + "\"", e);
    }
  }

  private void mergeApiDefs(TF_ApiDefMap apiDefMap, TF_Status status) {
    try (Stream<Path> s = Files.walk(apiDefsPath)) {
      s.filter(p -> p.toString().endsWith(".pbtxt"))
          .forEach(
              p -> {
                try {
                  byte[] content = Files.readAllBytes(p);
                  tensorflow.TF_ApiDefMapPut(
                      apiDefMap, new BytePointer(content), content.length, status);
                  status.throwExceptionIfNotOK();
                } catch (IOException e) {
                  throw new RuntimeException(
                      "Failed to parse API definition in resource file \"" + p.toString() + "\"",
                      e);
                }
              });
    } catch (IOException e) {
      throw new RuntimeException(
          "Failed to browse API definitions in resource folder \"" + apiDefsPath + "\"", e);
    }
  }

  private void createApiDef(OpDef opDef, File apiDefFile) throws IOException {
    System.out.println("Creating Java API definition for operator " + opDef.getName());
    var apiDef = ApiDef.newBuilder();
    apiDef.setGraphOpName(opDef.getName());

    ApiDef.Visibility visibility = null;
    do {
      System.out.print(
          "    Choose visibility of this op [v]isible/[h]idden/[s]kip/[d]efault (default=v): ");
      var value = USER_PROMPT.nextLine().trim();
      if (!value.isEmpty()) {
        switch (value) {
          case "V":
          case "v":
            visibility = ApiDef.Visibility.VISIBLE;
            apiDef.setVisibility(visibility);
            break;
          case "H":
          case "h":
            visibility = ApiDef.Visibility.HIDDEN;
            apiDef.setVisibility(visibility);
            break;
          case "S":
          case "s":
            visibility = ApiDef.Visibility.SKIP;
            apiDef.setVisibility(visibility);
            break;
          case "D":
          case "d":
            visibility = ApiDef.Visibility.DEFAULT_VISIBILITY;
            break;
          default:
            break;
        }
      } else {
        visibility = ApiDef.Visibility.VISIBLE;
      }
    } while (visibility == null);

    if (visibility != ApiDef.Visibility.SKIP) {
      var endpointNameBuilder = new StringBuilder();

      System.out.print("    Set the group of this op (default=core): ");
      var groupName = USER_PROMPT.nextLine().trim();
      if (!groupName.isEmpty() && !groupName.equals("core")) {
        endpointNameBuilder.append(groupName).append(".");
      }
      System.out.print("    Set the name of this op (default=" + opDef.getName() + "): ");
      var opCustomName = USER_PROMPT.nextLine().trim();
      endpointNameBuilder.append(opCustomName.isEmpty() ? opDef.getName() : opCustomName);

      var endpoint = ApiDef.Endpoint.newBuilder();
      endpoint.setName(endpointNameBuilder.toString());
      apiDef.addEndpoint(endpoint);
    }
    if (!apiDefFile.exists() && !apiDefFile.createNewFile()) {
      System.err.println("Cannot create API definition file \"" + apiDefFile.getPath() + "\"");
    }
    try (var apiDefWriter = new FileWriter(apiDefFile, StandardCharsets.UTF_8)) {
      var apiDefs = ApiDefs.newBuilder();
      apiDefs.addOp(apiDef.build());
      apiDefWriter.write(TextFormat.printer().printToString(apiDefs.build()));

    } catch (Exception e) {
      // If something goes wrong, erase the file we've just created
      if (!apiDefFile.delete()) {
        System.err.println(
            "Cannot delete invalid API definition file \""
                + apiDefFile.getPath()
                + "\", please clean up manually");
      }
      throw e;
    }
  }

  private void writeToFile(TypeSpec spec, String packageName) {
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
  private void generate(OpList opList) {
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
          writeToFile(spec, def.packageName);
        });

    statefulPairs.forEach(
        (pair) -> {
          pair.buildOpClasses().forEach((spec) -> writeToFile(spec, pair.getPackageName()));
        });
  }
}
