package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import life.genny.qwanda.entity.BaseEntity;

public class BaseEntityMap extends EntityMap<String, BaseEntity> {

  private final static String query = "select code from BaseEntity";
  private final static String ATTRIBUTE_NAME = "code";

  {
    super.putOnMapByTypeFunction = putOnMapByType();
    super.cloneFieldIdFunction = cloneFieldId();
  }


  public BaseEntityMap() {
    super(BaseEntity.class, query, ATTRIBUTE_NAME);
  }

  @Override
  public Function<BaseEntity, Map<String, BaseEntity>> putOnMapByType() {
    return (BaseEntity bas) -> new HashMap<String, BaseEntity>() {
      {
        put(bas.getCode(), bas);
      }
    };
  }

  @Override
  public BiFunction<BaseEntity, BaseEntity, BaseEntity> cloneFieldId() {
    return (bes1, bes2) -> {
      bes2.setId(bes1.getId());
      return bes2;
    };
  }
}
