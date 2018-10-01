package life.genny.map.task;

import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;
import life.genny.qwanda.validation.Validation;

public enum MapName {


  BASEENTITY(BaseEntity.class.getSimpleName()),
  
  ATTRIBUTE(Attribute.class.getSimpleName()),
  
  ANSWER(Answer.class.getSimpleName()),
  
  QUESTION(Question.class.getSimpleName()),
  
  QBASEMSGMESSAGETEMPLATE(QBaseMSGMessageTemplate.class.getSimpleName()),
  
  VALIDATION(Validation.class.getSimpleName()),

  ASK(Ask.class.getSimpleName());

  
  final String mapName;

  public String getName() {
    return mapName;
  }

  MapName(String name) {
    this.mapName = name;
  }

  @Override
  public String toString() {
    return mapName;
  }
}
