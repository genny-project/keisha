package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import life.genny.qwanda.Answer;


/**
 * 
 * Boilerplate code needed to inherit MapStore implemented methods with generic types. Classes that
 * extends EntityMap need to initialize functional values and fields for parent constructor.
 * 
 * @author helios
 *
 */
public class AnswerMap extends EntityMap<Long, Answer> {

  /**
   * Query to get Keys from store (e.g. Mysql).
   */
  private final static String query = "select id from Answer";

  /**
   * Name of the key attribute.
   */
  private final static String ATTRIBUTE_NAME = "id";


  {
    /**
     * Call methods at object initialization to declare parent functional values
     */
    super.putOnMapByTypeFunction = putOnMapByType();
  }

  public AnswerMap() {
    super(Answer.class, query, ATTRIBUTE_NAME);
  }


  @Override
  public Function<Answer, Map<Long, Answer>> putOnMapByType() {
    return (Answer answ) -> new HashMap<Long, Answer>() {
      {
        put(answ.getId(), answ);
      }
    };
  }
 
}
