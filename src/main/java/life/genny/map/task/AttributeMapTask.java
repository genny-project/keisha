package life.genny.map.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.message.QDataAttributeMessage;

public class AttributeMapTask extends EntityMapTask<String, Attribute> {

  
  public AttributeMapTask() {
    super(Registration.ATTRIBUTE);
  }

  @Override
  public Attribute create(Attribute value) {
    return getMap().put(value.getCode(), value);

  }

  public QDataAttributeMessage fetchAttributes() {
    final List<Attribute> entitys = new ArrayList(getMap().values());
    Attribute[] atArr = new Attribute[entitys.size()];
    atArr = entitys.toArray(atArr);
    QDataAttributeMessage msg = new QDataAttributeMessage(atArr);
    // msg.setToken(securityService.getToken());
    return msg;
  }

  public QDataAttributeMessage fetchAttribute(String attributeCode) {
    final Attribute attribute = getByKey(attributeCode);
    Attribute[] atArr = new Attribute[1];
    atArr[0] = attribute;
    QDataAttributeMessage msg = new QDataAttributeMessage(atArr);
    // msg.setToken(securityService.getToken());
    return msg;
  }

  public Attribute fetchAttributeById(final Long id) {
    return fetchById(id);

  }


}
