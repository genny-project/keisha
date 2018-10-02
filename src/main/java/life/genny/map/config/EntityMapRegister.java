package life.genny.map.config;

import life.genny.map.impl.AnswerMap;
import life.genny.map.impl.AskMap;
import life.genny.map.impl.AttributeMap;
import life.genny.map.impl.BaseEntityMap;
import life.genny.map.impl.EntityMap;
import life.genny.map.impl.QuestionMap;
import life.genny.map.impl.TemplateMap;
import life.genny.map.impl.ValidationMap;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;
import life.genny.qwanda.validation.Validation;

public enum EntityMapRegister {

  BASEENTITY(BaseEntity.class.getSimpleName(), new BaseEntityMap()),

  ATTRIBUTE(Attribute.class.getSimpleName(), new AttributeMap()),

  ANSWER(Answer.class.getSimpleName(), new AnswerMap()),

  QUESTION(Question.class.getSimpleName(), new QuestionMap()),

  QBASEMSGMESSAGETEMPLATE(QBaseMSGMessageTemplate.class.getSimpleName(), new TemplateMap()),

  VALIDATION(Validation.class.getSimpleName(), new ValidationMap()),

  ASK(Ask.class.getSimpleName(), new AskMap());

  private final EntityMap entityMapImp;

  final String mapName;

  public String getName() {
    return mapName;
  }

  EntityMapRegister(String name, EntityMap em) {
    System.out.println("created");
    this.mapName = name;
    this.entityMapImp = em;
  }

  public EntityMap getEntityMapImp() {
    return entityMapImp;
  }

  @Override
  public String toString() {
    return mapName;
  }
}
