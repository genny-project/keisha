package life.genny.map.config;

import com.hazelcast.config.Config;
import life.genny.qwandautils.GennySettings;

public class HazelcastConfigServer {

  protected static Config hazelcastServerConfig() {
    Config config = new Config();
    config.getGroupConfig().setName(GennySettings.username);
    config.getGroupConfig().setPassword(GennySettings.username);
    LoaderMapStore loadAll = new LoaderMapStore(config);
    return loadAll.config;
  }

}
