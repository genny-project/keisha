package life.genny.map.main;

import com.hazelcast.core.HazelcastInstance;
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
    HazelcastInstance hazelcast = server.startServer();
  }
  

}
