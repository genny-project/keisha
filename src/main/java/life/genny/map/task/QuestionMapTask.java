package life.genny.map.task;

import life.genny.qwanda.Question;

public class QuestionMapTask extends EntityMapTask<String,Question>{

  
  public QuestionMapTask() {
    super(MapName.QUESTION);
  }

  @Override
  public Question create(Question value) {
    return getMap().put(value.getCode(), value);
  }
}