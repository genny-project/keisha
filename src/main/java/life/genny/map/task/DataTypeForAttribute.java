package life.genny.map.task;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import life.genny.qwanda.Answer;
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

public enum DataTypeForAttribute {

  INTEGER_FULLY_QUALIFIED(applyIntType(), isInteger()), 
  INTEGER_SIMPLE_NAME(applyIntType(),isInteger()),

  LOCALDATETIME_FULLY_QUALIFIED(applyLocalDateTimeType(), isLocalDateTime()),
  LOCALDATETIME_SIMPLE_NAME(applyLocalDateTimeType(), isLocalDateTime()),

  LONG_FULLY_QUALIFIED(applyLongType(), isLong()),
  LONG_SIMPLE_NAME(applyLongType(), isLong()),

  LOCALTIME_FULLY_QUALIFIED(applyTimeType(), isLocalDate()), 
  LOCALTIME_SIMPLE_NAMEE(applyTimeType(),isLocalDate()),

  MONEY_FULLY_QUALIFIED(applyMoneyType(), isMoney()),
  MONEY_SIMPLE_NAME(applyMoneyType(), isMoney()),

  DOUBLE_FULLY_QUALIFIED(applyDoubleType(), isDouble()), 
  DOUBLE_SIMPLE_NAME(applyDoubleType(),isDouble()),

  BOOLEAN_FULLY_QUALIFIED(applyBooleanType(), isBoolean()), 
  BOOLEAN_SIMPLE_NAMEE(applyBooleanType(), isBoolean()),

  LOCALDATE_FULLY_QUALIFIED(applyLocalDateType(),isLocalDateTime()), 
  LOCALDATE_SIMPLE_NAME(applyLocalDateType(), isLocalDateTime()),

  STRING_FULLY_QUALIFIED(applyTextType(), isString()), 
  STRING_SIMPLE_NAME(applyTextType(),isString());


  private final Predicate<Optional<String>> isDataTypeForAttribute;
  private final Function<Answer, Attribute> getAttributeTyped;

  private DataTypeForAttribute(Function<Answer, Attribute> dataTypeForAttr,
      Predicate<Optional<String>> isDataTypeForAttribute) {
    this.getAttributeTyped = dataTypeForAttr;
    this.isDataTypeForAttribute = isDataTypeForAttribute;

  }


  public Predicate<Optional<String>> isDataTypeForAttribute() {
    return isDataTypeForAttribute;
  }


  private static Predicate<Optional<String>> isInteger() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.Integer") || type.equals("Integer")).orElse(false);
  }

  private static Predicate<Optional<String>> isString() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.String") || type.equals("String")).orElse(false);
  }

  private static Predicate<Optional<String>> isDouble() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.Double") || type.equals("Double")).orElse(false);
  }

  private static Predicate<Optional<String>> isBoolean() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.Boolean") || type.equals("Boolean")).orElse(false);
  }

  private static Predicate<Optional<String>> isLocalDate() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.LocalDate") || type.equals("LocalDate")).orElse(false); 
  }

  private static Predicate<Optional<String>> isLocalDateTime() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.LocalDateTime") || type.equals("LocalDateTime")).orElse(false); 
  }

  private static Predicate<Optional<String>> isMoney() {
    return (optType) -> optType
        .map(type -> type.equals("org.javamoney.moneta.Money") || type.equals("Money")).orElse(false); 
  }

  private static Predicate<Optional<String>> isLong() {
    return (optType) -> optType
        .map(type -> type.equals("java.lang.Long") || type.equals("Long")).orElse(false); 
  }

  public Function<Answer, Attribute> getDataTypeForAttribute() {
    return getAttributeTyped;
  }

  private static Function<Answer, Attribute> applyIntType() {
    return (entity) -> new AttributeInteger(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyLocalDateTimeType() {
    return (entity) -> new AttributeDateTime(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyLongType() {
    return (entity) -> new AttributeLong(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyTimeType() {
    return (entity) -> new AttributeTime(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyMoneyType() {
    return (entity) -> new AttributeMoney(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyDoubleType() {
    return (entity) -> new AttributeDouble(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyBooleanType() {
    return (entity) -> new AttributeBoolean(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyLocalDateType() {
    return (entity) -> new AttributeDate(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

  private static Function<Answer, Attribute> applyTextType() {
    return (entity) -> new AttributeText(entity.getAttributeCode(),
        StringUtils.capitalize(entity.getAttributeCode().substring(4).toLowerCase()));
  }

}
