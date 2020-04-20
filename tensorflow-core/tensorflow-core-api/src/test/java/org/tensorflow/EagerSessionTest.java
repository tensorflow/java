/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.EagerSession.ResourceCleanupStrategy;

@RunWith(JUnit4.class)
public class EagerSessionTest {

  @Test
  public void closeSessionTwiceDoesNotFail() {
    try (EagerSession s = EagerSession.create()) {
      s.close();
    }
  }

  @Test
  public void cleanupResourceOnSessionClose() {
    Pointer ref;
    try (EagerSession s =
        EagerSession.options()
            .resourceCleanupStrategy(ResourceCleanupStrategy.ON_SESSION_CLOSE)
            .build()) {
      s.attach(ref = new IntPointer(1));
      assertFalse(ref.isNull());

      // check that reaching safe point did not release resources
      buildOp(s);
      assertFalse(ref.isNull());
    }
    assertTrue(ref.isNull());
  }

  @Test
  public void cleanupResourceOnSafePoints() {
    try (EagerSession s =
        EagerSession.options()
            .resourceCleanupStrategy(ResourceCleanupStrategy.ON_SAFE_POINTS)
            .build()) {

      Pointer ref = new IntPointer(1);
      s.attach(ref);
      assertFalse(ref.isNull());

      // garbage collecting the reference won't release until we reached safe point
      s.detach(ref);
      assertFalse(ref.isNull());
      buildOp(s); // safe point
      assertTrue(ref.isNull());
    }
  }

  @Test
  public void cleanupResourceInBackground() {
    try (EagerSession s =
        EagerSession.options()
            .resourceCleanupStrategy(ResourceCleanupStrategy.IN_BACKGROUND)
            .build()) {

      Pointer ref = new IntPointer(1024 * 1024);
      s.attach(ref);
      assertFalse(ref.isNull());
      System.gc();
      sleep(50); // allow some time to the background thread for cleaning up resources

      long before = Pointer.totalBytes();
      s.detach(ref.retainReference());
      ref = null;
      System.gc();
      sleep(50); // allow some time to the background thread for cleaning up resources
      long after = Pointer.totalBytes();
      assertEquals(4 * 1024 * 1024, before - after);
    }
  }

  @Test
  public void clearedResourcesAreNotCleanedUp() {
    Pointer ref;
    try (EagerSession s = EagerSession.create()) {
      s.attach(ref = new IntPointer(1));
      s.detach(ref.retainReference());
    }
    assertFalse(ref.isNull());
  }

  @Test
  public void buildingOpWithClosedSessionFails() {
    EagerSession s = EagerSession.create();
    s.close();
    try {
      buildOp(s);
      fail();
    } catch (IllegalStateException e) {
      // ok
    }
  }

  @Test
  public void addingReferenceToClosedSessionFails() {
    EagerSession s = EagerSession.create();
    s.close();
    try {
      s.attach(new IntPointer(1));
      fail();
    } catch (IllegalStateException e) {
      // ok
    }
  }

  @Test
  public void defaultSession() throws Exception {
    EagerSession.closeDefaultForTest();
    EagerSession.Options options =
        EagerSession.options().resourceCleanupStrategy(ResourceCleanupStrategy.ON_SESSION_CLOSE);
    EagerSession.initDefault(options);
    EagerSession session = EagerSession.getDefault();
    assertNotNull(session);
    assertEquals(ResourceCleanupStrategy.ON_SESSION_CLOSE, session.resourceCleanupStrategy());
    try {
      EagerSession.initDefault(options);
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
    try {
      session.close();
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
  }

  private static void buildOp(EagerSession s) {
    // Creating an operation is a safe point for resource cleanup
    try {
      s.opBuilder("Const", "Const");
    } catch (UnsupportedOperationException e) {
      // TODO (karlllessard) remove this exception catch when EagerOperationBuilder is implemented
    }
  }

  private static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
  }
}
