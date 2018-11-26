package life.genny.map.main;

import life.genny.map.server.ApplicationServer;

/**
 * 
 * 
 * @author helios
 *
 */
public class ApplicationMain {



    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationServer server = new ApplicationServer();
        server.startServer();
    }


}
