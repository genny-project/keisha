package life.genny.map.task;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;

public class DescendingComparator implements Comparator<Map.Entry>, Serializable {

  private static BiFunction<Map.Entry, Map.Entry, Integer> compareFunc;

  @Override
  public int compare(Map.Entry e1, Map.Entry e2) {
    return compareFunc.apply(e1, e2).intValue();
  }

  public void setFunc(BiFunction<Map.Entry, Map.Entry, Integer> a,Class clazz) {
    compareFunc = inferType(a, clazz);
  }

  public BiFunction<Map.Entry, Map.Entry, Integer> inferType(BiFunction<Map.Entry, Map.Entry, Integer> o,
      Class<?> clazz) {
    if (clazz.equals(Long.class)) {
      return o;
    } else
      return null;
  }
  
}
