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
 =======================================================================

 */
package org.tensorflow;

import org.bytedeco.javacpp.Pointer;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tensorflow.internal.c_api.TFLogEntry;
import org.tensorflow.internal.c_api.TFLogSink;
import org.tensorflow.internal.c_api.global.tensorflow;

public class NativeLogSink extends TFLogSink {
  private static final ILoggerFactory factory = LoggerFactory.getILoggerFactory();
  private static final Logger logger = LoggerFactory.getLogger(NativeLogSink.class);
  NativeLogSink() {
    super();
  }

  @Override
  public void Send(TFLogEntry entry) {
    //TODO make work, blocked by https://github.com/tensorflow/tensorflow/issues/44995#issuecomment-869091090
    System.out.printf(
        "Log message: Severity: %d, Fname: %s, line: %s, string: %s\n", entry.log_severity(), entry.FName().getString(), entry.Line(), entry.ToString().getString());
//    Logger logger = factory.getLogger(entry.FName().getString());
//    switch (entry.log_severity()){
//      case tensorflow.kWarning:
//        logger.warn(entry.ToString().getString());
//        break;
//      case tensorflow.kError:
//      case tensorflow.kFatal:
//        logger.error(entry.ToString().getString());
//        break;
//      default:
//        logger.info(entry.ToString().getString());
//    }
  }

  @Override
  public void WaitTillSent() {
  }
}
