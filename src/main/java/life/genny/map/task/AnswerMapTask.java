package life.genny.map.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;
import com.hazelcast.core.IMap;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.Answer;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QDataAnswerMessage;



public class AnswerMapTask extends EntityMapTask<Long, Answer> {


  private final String PREFIX_PRI_IS = "PRI_IS_";


  public AnswerMapTask() {
    super(Registration.ANSWER);
  }
  
  public IMap<Long, Answer> getAnswerMap() {
    return getMap();
  }

  public Long getNewId() {
    return getMap().keySet().stream()
        .sorted(Collections.reverseOrder())
        .findFirst().get() + 1;
  }
  
  public Answer fetchAnswerById(final Long id ) {
    return fetchById(id);
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
