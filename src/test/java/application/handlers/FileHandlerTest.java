package application.handlers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileHandlerTest {
  @Test
  public void fileHandlerCanGetTheMIMEType() {
    FileHandler fileHandler = new FileHandler("/index.html");
    String mimeType = fileHandler.getMIMEType();

    assertEquals(mimeType, "text/html");
  }

  @Test
  public void fileHandlerCanGetTheFileAsBytes() {
    FileHandler fileHandler = new FileHandler("/index.html");
    byte[] fileAsBytes = fileHandler.getFileAsBytes();
    assertTrue(new String(fileAsBytes).contains("<TITLE>Caroline's TTT</TITLE>"));
  }
}