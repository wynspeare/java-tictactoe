package application.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
