package life.genny.map.impl;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface ImplementByType<T>  {
	
	 <A> Function<T, Map<A, T>> putOnMapByType();
	 
	 BiFunction<T, T, T> cloneFieldId();

}
