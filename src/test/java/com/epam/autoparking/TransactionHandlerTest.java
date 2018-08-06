package com.epam.autoparking;

import com.epam.autoparking.exceptions.FileReadFailedException;
import org.junit.Assert;
import org.junit.Test;


public class TransactionHandlerTest {
  @Test
  public void testValid() throws FileReadFailedException {
    TransactionHandler.getInstance().setFilePath("does/not/exist");
    Assert.assertFalse(TransactionHandler.getInstance().isValid());

  }
}
