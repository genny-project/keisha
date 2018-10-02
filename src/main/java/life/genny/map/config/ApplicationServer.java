package life.genny.map.config;

import com.hazelcast.core.Hazelcast;

public class ApplicationServer {

  public void startServer() {
    Hazelcast.newHazelcastInstance(HazelcastConfigServer.hazelcastServerConfig());
  }

}
