package application.handlers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileHandlerTest {
  @Test
  public void fileHandlerCanGetTheMIMEType() {
    FileHandler fileHandler = new FileHandler("index.html");
    String mimeType = fileHandler.getMIMEType();

    assertEquals(mimeType, "text/html");
  }
}