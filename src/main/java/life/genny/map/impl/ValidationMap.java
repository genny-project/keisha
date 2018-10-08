package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import life.genny.qwanda.validation.Validation;

public class ValidationMap extends EntityMap<String, Validation> {

  private final static String query = "select code from Validation";
  private final static String ATTRIBUTE_NAME = "code";
  
  {
    super.putOnMapByTypeFunction = putOnMapByType();
    super.cloneFieldIdFunction = cloneFieldId();
  }

  public ValidationMap() {
    super(Validation.class, query, ATTRIBUTE_NAME);
  }

  @Override
  public Function<Validation, Map<String, Validation>> putOnMapByType() {
    return (Validation base) -> new HashMap<String, Validation>() {
      {
        put(base.getCode(), base);
      }
    };
  }

  @Override
  public BiFunction<Validation, Validation, Validation> cloneFieldId() {
    return (bes1, bes2) -> {
      bes2.setId(bes1.getId());
      return bes2;
    };
  }

}
