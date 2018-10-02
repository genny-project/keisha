package life.genny.map.main;

import life.genny.map.config.ApplicationServer;

public class ApplicationMain {

  public static void main(String[] args) {
    ApplicationServer server = new ApplicationServer();
    server.startServer();
  }

}


