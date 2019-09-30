package application;

import org.junit.Test;
import server.Router;
import server.logger.LoggerSpy;

import static org.junit.Assert.*;

public class TTTTest {
  @Test
  public void importedServerJARCanBeTested() {
    LoggerSpy testLogger = new LoggerSpy();
    Router router = new Router(testLogger);

    assertTrue(router.routes.isEmpty());
  }
}