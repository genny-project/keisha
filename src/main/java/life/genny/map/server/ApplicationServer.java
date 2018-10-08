package life.genny.map.server;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import life.genny.map.config.MainConfig;

/**
 * @author helios
 *
 */
public class ApplicationServer {

  /**
   * Start Hazelcast in server mode.
   * <p>
   * @return 
   * @since 1.0
   */
  public HazelcastInstance startServer() {
    Config config = MainConfig.getServerConfig();
    return Hazelcast.newHazelcastInstance(config);
  }

}
