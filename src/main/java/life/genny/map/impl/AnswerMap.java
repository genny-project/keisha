package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import life.genny.qwanda.Answer;


public class AnswerMap extends EntityMap<Long, Answer>{
	
	private final static String query = "select id from Answer";
	private final static String ATTRIBUTE_NAME = "id";
	
	{
		super.putOnMapByTypeFunction = putOnMapByType();
		super.cloneFieldIdFunction = super.cloneFieldId();
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
