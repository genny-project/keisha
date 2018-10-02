package life.genny.map.task;

import life.genny.map.config.EntityMapRegister;
import life.genny.qwanda.Question;

public class QuestionMapTask extends EntityMapTask<String,Question>{

  
  public QuestionMapTask() {
    super(EntityMapRegister.QUESTION);
  }

  @Override
  public Question create(Question value) {
    return getMap().put(value.getCode(), value);
  }
  
  public Question fetchQuestionById() {
    return null;
  }
}