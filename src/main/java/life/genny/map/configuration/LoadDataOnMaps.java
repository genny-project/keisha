package life.genny.map.configuration;

import java.util.ArrayList;
import java.util.List;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import life.genny.map.implementation.AnswerMap;
import life.genny.map.implementation.AskMap;
import life.genny.map.implementation.AttributeMap;
import life.genny.map.implementation.BaseEntityMap;
import life.genny.map.implementation.EntityMap;
import life.genny.map.implementation.QuestionMap;
import life.genny.map.implementation.TemplateMap;
import life.genny.map.implementation.ValidationMap;
import life.genny.map.task.MapName;

public class LoadDataOnMaps {

  protected final Config config;

  public LoadDataOnMaps(Config config) {
    this.config = loadAll(config);
  }

  private final List<EntityMap> registeredMaps = new ArrayList<EntityMap>() {
    {
      add(new BaseEntityMap());
      add(new ValidationMap());
      add(new TemplateMap());
      add(new AttributeMap());
      add(new QuestionMap());
      add(new AnswerMap());
      add(new AskMap());
    }
  };

  private Config loadAll(Config config) {
    registeredMaps.stream().forEach(ent -> {
      MapStoreConfig implementation = implementationConfig(ent);
      String classNameUpperCase = ent.getEntityClass().getSimpleName().toUpperCase();
      MapName valueOf = MapName.valueOf(classNameUpperCase);
      MapConfig mapConfig = config.getMapConfig(valueOf.getName());
      mapConfig.setMapStoreConfig(implementation);
    });
    return config;
  }


  private MapStoreConfig implementationConfig(EntityMap ent) {
    MapStoreConfig implementation = new MapStoreConfig().setImplementation(ent);
    if (ent instanceof BaseEntityMap)
      ;
    else if (ent instanceof AttributeMap)
      ;
    else if (ent instanceof ValidationMap)
      ;
    else if (ent instanceof TemplateMap)
      ;
    else if (ent instanceof AnswerMap)
      ;
    else if (ent instanceof QuestionMap)
      ;
    return implementation;
  }

}
