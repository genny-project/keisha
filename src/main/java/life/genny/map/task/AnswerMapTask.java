package life.genny.map.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.persistence.NoResultException;
import org.apache.commons.lang3.StringUtils;
import com.hazelcast.core.IMap;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeBoolean;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeDouble;
import life.genny.qwanda.attribute.AttributeInteger;
import life.genny.qwanda.attribute.AttributeLong;
import life.genny.qwanda.attribute.AttributeMoney;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.AttributeTime;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.message.QDataAnswerMessage;



public class AnswerMapTask extends EntityMapTask<Long, Answer> {


  private final String PREFIX_PRI_IS = "PRI_IS_";


  public AnswerMapTask() {
    super(MapName.ANSWER);
  }
  
  public IMap<Long, Answer> getAnswerMap() {
    return getMap();
  }

  public Long getNewId() {
    return getMap().keySet().stream()
        .sorted(Collections.reverseOrder())
        .findFirst().get() + 1;
  }
  

  @Override
  public Answer create(Answer value) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public void saveBulk(final QDataAnswerMessage entitys) {

    Stream<DataTypeForAttribute> typesForAttrEnum = Arrays.stream(DataTypeForAttribute.values());
    Stream<Answer> answers = Arrays.stream(entitys.getItems());

    Long id = getNewId();
    AttributeMapTask attributeMapResource = new AttributeMapTask();

    answers.forEach(answer -> {
      Optional<Attribute> attributeIfAny = Optional.ofNullable(answer.getAttribute());

      if (attributeIfAny.isPresent())
        getMap().put(id, answer);
      else {
        Attribute attr = attributeMapResource.getByKey(answer.getAttributeCode());
        Optional<Attribute> attributeFromMapIfAny = Optional.ofNullable(attr);
        
        attributeFromMapIfAny.ifPresent(answer::setAttribute);

        if (! attributeFromMapIfAny.isPresent()) {
          if (answer.getAttributeCode().startsWith(PREFIX_PRI_IS)) {
            Attribute attribute = DataTypeForAttribute.BOOLEAN_FULLY_QUALIFIED
                .getDataTypeForAttribute().apply(answer);
            attributeMapResource.getMap().put(attribute.getCode(), attribute);
          } else {
            DataTypeForAttribute chosenAttributeType = typesForAttrEnum
                .filter(type -> type.isDataTypeForAttribute()
                    .test(Optional.ofNullable(answer.getDataType())))
                .findFirst().orElse(DataTypeForAttribute.STRING_FULLY_QUALIFIED);
            Attribute attribute = chosenAttributeType.getDataTypeForAttribute().apply(answer);
            attributeMapResource.getMap().put(attribute.getCode(), attribute);
            answer.setAttribute(attribute);
            getMap().put(id, answer);
          }
        }
        answer.setAttribute(attr);
      }
    });
  }



}
