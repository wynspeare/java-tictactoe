package application;

import org.junit.Test;
import server.IController;
import server.Router;
import server.TTTController;
import server.logger.LoggerSpy;

import static org.junit.Assert.*;

public class TTTTest {
  @Test
  public void importedServerJARCanBeTested() {
    LoggerSpy testLogger = new LoggerSpy();
    IController controller = new TTTController();
    Router router = new Router(testLogger, controller);

    assertTrue(router.routes.isEmpty());
  }
}