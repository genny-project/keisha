package life.genny.map.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;

public class TemplateMap extends EntityMap<String, QBaseMSGMessageTemplate> {

  private final static String query = "select code from QBaseMSGMessageTemplate";
  private final static String ATTRIBUTE_NAME = "code";

  {
    super.putOnMapByTypeFunction = putOnMapByType();
    super.cloneFieldIdFunction = cloneFieldId();
  }

  public TemplateMap() {
    super(QBaseMSGMessageTemplate.class, query, ATTRIBUTE_NAME);
  }

  @Override
  public Function<QBaseMSGMessageTemplate, Map<String, QBaseMSGMessageTemplate>> putOnMapByType() {
    return (QBaseMSGMessageTemplate base) -> new HashMap<String, QBaseMSGMessageTemplate>() {
      {
        put(base.getCode(), base);
      }
    };
  }

  @Override
  public BiFunction<QBaseMSGMessageTemplate, QBaseMSGMessageTemplate, QBaseMSGMessageTemplate> cloneFieldId() {
    return (bes1, bes2) -> {
      bes2.setId(bes1.getId());
      return bes2;
    };
  }

}
