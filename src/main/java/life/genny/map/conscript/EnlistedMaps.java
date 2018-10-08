package life.genny.map.conscript;

import life.genny.map.impl.EntityMap;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;
import life.genny.qwanda.validation.Validation;

interface EnlistedMaps {
  
  public final static String BASEENTITY_MAP = BaseEntity.class.getSimpleName();

  public final static String ATTRIBUTE_MAP = Attribute.class.getSimpleName();

  public final static String ANSWER_MAP = Answer.class.getSimpleName();

  public final static String QUESTION_MAP = Question.class.getSimpleName();

  public final static String TEMPLATE_MAP = QBaseMSGMessageTemplate.class.getSimpleName();

  public final static String VALIDATION_MAP = Validation.class.getSimpleName();
  
  public final static String ASK_MAP = Ask.class.getSimpleName();
  
  
  EntityMap register();


}
