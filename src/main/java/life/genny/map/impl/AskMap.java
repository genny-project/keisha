package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import life.genny.qwanda.Ask;

public class AskMap extends EntityMap<Long, Ask> {

  private final static String query = "select id from Ask";
  private final static String ATTRIBUTE_NAME = "id";


  {
    super.putOnMapByTypeFunction = putOnMapByType();
    super.cloneFieldIdFunction = cloneFieldId();
  }

  public AskMap() {
    super(Ask.class, query, ATTRIBUTE_NAME);
  }

  @Override
  public Function<Ask, Map<Long, Ask>> putOnMapByType() {
    return (Ask ask) -> new HashMap<Long, Ask>() {
      {
        put(ask.getId(), ask);
      }
    };
  }
}
