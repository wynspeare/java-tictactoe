package application.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileHandler {
  String requestedResource;

  public FileHandler(String requestedResource) {
    this.requestedResource = requestedResource;
  }

  public boolean fileExists() {
    File file = new File("./assets/public" + requestedResource);
    return file.exists();
  }

  public String stringifyFile() {
    InputStream in = this.getClass().getClassLoader()
            .getResourceAsStream("assets/public" + requestedResource);
    return new BufferedReader(new InputStreamReader(in))
            .lines().collect(Collectors.joining("\n"));
  }

  public byte[] getFileAsBytes() {
    byte[] convertedFile = null;
    try {
      convertedFile = Files.readAllBytes(Paths.get("assets/public" + requestedResource));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return convertedFile;
  }

  public String getMIMEType() {
    Path path = new File("assets/public" + requestedResource).toPath();
    String mimeType = null;
    try {
      mimeType = Files.probeContentType(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mimeType;
  }
}
