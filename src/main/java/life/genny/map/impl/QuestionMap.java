package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import life.genny.qwanda.Question;

public class QuestionMap extends EntityMap<String,Question> {

	private final static String query = "select code from Question";
	private final static String ATTRIBUTE_NAME = "code";

	{
		super.putOnMapByTypeFunction = putOnMapByType();
		super.cloneFieldIdFunction = cloneFieldId();
	}

	private static BiFunction<Question, Question, Question> copyId = (ques1, ques2) -> {
		ques2.setId(ques1.getId());
		return ques2;
	};

	public QuestionMap() {
		super(Question.class, query, ATTRIBUTE_NAME);
	}

	@Override
	public Function<Question, Map<String, Question>> putOnMapByType() {
		return (Question base) -> new HashMap<String, Question>() {
			{
				put(base.getCode(), base);
			}
		};
	}

	@Override
	public BiFunction<Question, Question, Question> cloneFieldId() {
		return (ques1, ques2) -> {
			ques2.setId(ques1.getId());
			return ques2;
		};
	}

}
