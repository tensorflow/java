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

import com.google.protobuf.UnknownFieldSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import org.tensorflow.proto.framework.OpList;

public class ConvertToTxt {

  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.err.println("Must pass the ops.pb file as input");
      System.exit(1);
    }

    File inputFile = new File(args[0]);

    if (!inputFile.exists()) {
      System.err.println("Passed input file " + inputFile + " does not exist.");
      System.exit(1);
    }

    if (!inputFile.isFile()) {
      System.err.println("Passed input file " + inputFile + " is not a file.");
      System.exit(1);
    }

    File outputFile;
    if (args.length > 1) {
      outputFile = new File(args[1]);
    } else {
      outputFile = new File(inputFile.getParentFile(), "ops.pbtxt");
    }

    OpList.Builder builder = OpList.parseFrom(new FileInputStream(inputFile)).toBuilder();
    builder.setUnknownFields(UnknownFieldSet.getDefaultInstance());
    builder.getOpBuilderList()
        .forEach(x -> x.setUnknownFields(UnknownFieldSet.getDefaultInstance()));
    OpList opList = builder.build();

    Files.write(
        outputFile.toPath(),
        opList.toString().getBytes(StandardCharsets.UTF_8),
        StandardOpenOption.WRITE,
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
  }

}
