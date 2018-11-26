package life.genny.map.server;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import life.genny.map.config.MainConfig;
import life.genny.qwandautils.GennySettings;
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
        config.getGroupConfig().setName(GennySettings.username);
        config.getGroupConfig().setPassword(GennySettings.username);
        HazelcastInstance haInst = Hazelcast.newHazelcastInstance(config);
        haInst.getMap(GennySettings.mainrealm);
        return haInst;
    }

}
