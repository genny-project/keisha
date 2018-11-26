package life.genny.map.config;

import com.hazelcast.config.Config;
import life.genny.qwandautils.GennySettings;

/**
 * 
 * @author helios
 * 
 */
public class MainConfig {
  
  /**
   * Main configuration object used in Hazelcast server mode.
   */
  private final static Config serverConfig;
  
  /**
   * Initialize the config at boot up.
   */
  static {
    serverConfig = configureServerConfig();
  }
  
  /**
   * @return <b>serverConfig</b> (a Config object used Hazelcast in server mode)
   */
  public static Config getServerConfig() {
    return serverConfig;
  }

  /**
   * @return <b>contextConfig</b> object with the context of serverConfig field.
   */
  private static Config configureServerConfig() {
    Config config = new Config();
    config.getGroupConfig().setName(GennySettings.username);
    config.getGroupConfig().setPassword(GennySettings.username);
    MapStoreLoader loader = new MapStoreLoader(config);
    loader.loadAll();
    Config contextConfig = loader.getContextConfig();
    return contextConfig;
  }

}
