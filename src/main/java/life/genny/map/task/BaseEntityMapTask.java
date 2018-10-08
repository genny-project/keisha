package life.genny.map.task;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.entity.BaseEntity;

public class BaseEntityMapTask extends EntityMapTask<String, BaseEntity> {

  static Comparator<Entry> descendingComparator;
  
  {
    super.descendingComparator = this.descendingComparator();
  }

  public BaseEntityMapTask() {
    super(Registration.BASEENTITY);
  }

  @Override
  public BaseEntity create(BaseEntity value) {
    return getMap().put(value.getCode(), value);
  }

  public BaseEntity fetchBaseEntityByCode(final String code) {
    return getByKey(code);
  }

  public BaseEntity fetchAttributesByBaseEntityCode(final String code) {
    return getByKey(code);
  }

//  public List<BaseEntity> getBaseEntityInPages() {
//    Collection<BaseEntity> values = getMap().values(paging);
//    return new ArrayList(values);
//  }

  @Override
  protected Comparator<Entry> descendingComparator() {
    // TODO Auto-generated method stub
    Comparator<Entry> comparator = new Comparator<Map.Entry>() {
      @Override
      public int compare(Map.Entry e1, Map.Entry e2) {
        BaseEntity s1 = (BaseEntity) e1.getValue();
        BaseEntity s2 = (BaseEntity) e2.getValue();
        System.out.println(s1.getId().intValue() - s2.getId().intValue());
        return  s1.getId().intValue() - s2.getId().intValue();
      }
    };
    return comparator;
  }

}


