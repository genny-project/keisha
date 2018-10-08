package life.genny.map.task;

import java.util.Comparator;
import java.util.Map.Entry;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.message.QBaseMSGMessageTemplate;

public class TemplateMapTask extends EntityMapTask<String,QBaseMSGMessageTemplate>{


  {
    super.descendingComparator = descendingComparator();
  }
  
  public TemplateMapTask() {
    super(Registration.QBASEMSGMESSAGETEMPLATE);
  }

  @Override
  public QBaseMSGMessageTemplate create(QBaseMSGMessageTemplate value) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Comparator<Entry> descendingComparator() {
    // TODO Auto-generated method stub
    return null;
  }


}