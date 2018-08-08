package com.epam.autoparking.persistance;

import com.epam.autoparking.persistance.FileReadFailedException;
import com.epam.autoparking.persistance.TransactionHandler;
import org.junit.Assert;
import org.junit.Test;


public class TransactionHandlerTest {
  @Test
  public void testValid() throws FileReadFailedException {
    TransactionHandler.getInstance().setFilePath("does/not/exist");
    Assert.assertFalse(TransactionHandler.getInstance().isValid());

  }
}
