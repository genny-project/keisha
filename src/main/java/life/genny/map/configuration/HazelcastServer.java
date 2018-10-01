package life.genny.map.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import life.genny.qwandautils.GennySettings;

public class HazelcastServer {
  
  public void startServer() {
    Hazelcast.newHazelcastInstance(hazelcastServerConfig());
  }
  
  private Config hazelcastServerConfig() {
    Config config = new Config();
     config.getGroupConfig().setName(GennySettings.username);
     config.getGroupConfig().setPassword(GennySettings.username);
    LoadDataOnMaps loadAll = new LoadDataOnMaps(config);
    return loadAll.config;
  }

}
