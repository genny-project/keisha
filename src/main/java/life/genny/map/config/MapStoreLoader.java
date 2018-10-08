package life.genny.map.config;

import java.util.Arrays;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import life.genny.map.conscript.Registration;
import life.genny.map.impl.AnswerMap;
import life.genny.map.impl.AttributeMap;
import life.genny.map.impl.BaseEntityMap;
import life.genny.map.impl.EntityMap;
import life.genny.map.impl.QuestionMap;
import life.genny.map.impl.TemplateMap;
import life.genny.map.impl.ValidationMap;

public class MapStoreLoader {

  /**
   * Config object for Hazelcast server within the context of MainConfig._serverConfig.
   */
  private final Config contextConfig;

  /**
   * Contructor needed to use existing Config object to refer its context.
   * 
   * @param Config Hazelcast
   */
  public MapStoreLoader(Config config) {
    this.contextConfig = addContextConfig(config);
  }

  private Config addContextConfig(Config config) {
    return config;
  }

  /**
   * Load all maps implementation registered in EntityMapRegister @see
   * life.genny.map.config.EntityMapRegister
   */
  public void loadAll() {
    Registration[] values = Registration.values();
    Arrays.asList(values).stream().forEach(entityMap -> {
      MapStoreConfig implementation = implementationConfig(entityMap.register());
      MapConfig mapConfig = this.contextConfig.getMapConfig(entityMap.getName());
      mapConfig.setMapStoreConfig(implementation);
    });
  }

  public Config getContextConfig() {
    return contextConfig;
  }

  /**
   * Configure the implementation for the EntityMap.
   * 
   * @param entityMap object
   * @return
   */
  private MapStoreConfig implementationConfig(EntityMap entityMap) {
    MapStoreConfig implementation = new MapStoreConfig().setImplementation(entityMap);
    if (entityMap instanceof BaseEntityMap)
      ;
    else if (entityMap instanceof AttributeMap)
      ;
    else if (entityMap instanceof ValidationMap)
      ;
    else if (entityMap instanceof TemplateMap)
      ;
    else if (entityMap instanceof AnswerMap)
      ;
    else if (entityMap instanceof QuestionMap)
      ;
    return implementation;
  }

}
