package life.genny.map.implementation;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

interface ImplementByType<T>  {
	
	 <A> Function<T, Map<A, T>> putOnMapByType();
	 
	 BiFunction<T, T, T> cloneFieldId();

}
