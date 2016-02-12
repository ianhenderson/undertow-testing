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
  public static void main(final String[] args) {
    String ENV_PORT = System.getenv("PORT");
    int port = (ENV_PORT != null) ? Integer.parseInt(ENV_PORT) : 8080 ;
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
