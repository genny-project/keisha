package life.genny.map.task;

import life.genny.map.config.EntityMapRegister;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;

public class TemplateMapTask extends EntityMapTask<String,QBaseMSGMessageTemplate>{

  public TemplateMapTask() {
    super(EntityMapRegister.QBASEMSGMESSAGETEMPLATE);
  }

  @Override
  public QBaseMSGMessageTemplate create(QBaseMSGMessageTemplate value) {
    // TODO Auto-generated method stub
    return null;
  }
}