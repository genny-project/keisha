package life.genny.map.config;

import java.util.Arrays;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import life.genny.map.impl.EntityMap;

public class LoaderMapStore {

  protected final Config config;

  public LoaderMapStore(Config config) {
    this.config = loadAll(config);
  }
  
  private Config loadAll(Config config) {
    Arrays.asList(EntityMapRegister.values()).stream().forEach(entityMap -> {
      MapStoreConfig implementation = implementationConfig(entityMap.getEntityMapImp());
      MapConfig mapConfig = config.getMapConfig(entityMap.getName());
      mapConfig.setMapStoreConfig(implementation);
    });
    return config;
  }

  private MapStoreConfig implementationConfig(EntityMap ent) {
    MapStoreConfig implementation = new MapStoreConfig().setImplementation(ent);
//    if (ent instanceof BaseEntityMap)
//      ;
//    else if (ent instanceof AttributeMap)
//      ;
//    else if (ent instanceof ValidationMap)
//      ;
//    else if (ent instanceof TemplateMap)
//      ;
//    else if (ent instanceof AnswerMap)
//      ;
//    else if (ent instanceof QuestionMap)
//      ;
    return implementation;
  }

}
