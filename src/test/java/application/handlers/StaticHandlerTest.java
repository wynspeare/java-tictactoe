//package application.handlers;
//
//import org.junit.Test;
//import server.Response;
//import server.request.Request;
//
//import static org.junit.Assert.*;
//
//public class StaticHandlerTest {
////  @Test
////  public void staticHandlerMakeAFileAString() {
////    StaticHandler staticHandler = new StaticHandler("assets/public/index.html");
////
////    String fileAsString = staticHandler.stringifyFile();
////    System.out.println(fileAsString);
////
//////    assertTrue(fileAsString instanceof String);
////  }
//  @Test
//  public void staticHandlerMakeAFileAString() {
//    StaticHandler staticHandler = new StaticHandler("../../../../../../assets/public/index.html");
//    Request request = new Request("GET /ttt HTTP/1.1");
//
//    Response staticResponse = staticHandler.buildResponse(request);
//
//    System.out.println(staticResponse.stringifyResponse());
//  //    assertTrue(fileAsString instanceof String);
//  }
//}