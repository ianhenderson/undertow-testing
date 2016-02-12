// package com.mastertheboss.undertow;
//
// /**
//  * Hello world!
//  *
//  */
// public class App
// {
//     public static void main( String[] args )
//     {
//         System.out.println( "Hello World!" );
//     }
// }

// ========================
// Undertow practice
package com.mastertheboss.undertow;

import io.undertow.Undertow;
import io.undertow.server.*;
import io.undertow.util.Headers;

public class App {
  private static boolean isEmpty(String s) {
    return (s == null || s.isEmpty());
  }

  public static void main(final String[] args) {
    String ENV_PORT = System.getenv("PORT");
    String SYS_PORT = System.getProperty("server.port");
    System.out.println("ENV_PORT: " + ENV_PORT);
    System.out.println("SYS_PORT: " + SYS_PORT);
    int port = (!isEmpty(ENV_PORT)) ?
      Integer.parseInt(ENV_PORT) :
      (!isEmpty(SYS_PORT)) ?
        Integer.parseInt(SYS_PORT) :
        8080 ;
    System.out.println("Listening on port: " + port);
    Undertow server = Undertow.builder().addHttpListener(port, "localhost")
      .setHandler(new HttpHandler() {
        public void handleRequest(final HttpServerExchange exchange) throws Exception {
          exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
          exchange.getResponseSender().send("Hello World");
        }
      }).build();
    server.start();
  }
}
