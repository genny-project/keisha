package life.genny.map.task;

import life.genny.map.config.EntityMapRegister;
import life.genny.qwanda.entity.BaseEntity;

public class BaseEntityMapTask extends EntityMapTask<String,BaseEntity>{

  
  public BaseEntityMapTask() {
    super(EntityMapRegister.BASEENTITY);
  }

  @Override
  public BaseEntity create(BaseEntity value) {
    return getMap().put(value.getCode(), value);
  }
  
  public BaseEntity fetchBaseEntityByCode(String code) {
    return getByKey(code);
  }
  
}


