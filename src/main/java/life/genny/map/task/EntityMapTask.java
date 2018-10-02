package life.genny.map.task;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import life.genny.map.config.EntityMapRegister;

public abstract class EntityMapTask<K, V> {


  private IMap<K, V> map;

  public IMap<K, V> getMap() {
    return map;
  }

  public V getByKey(K key) {
    return getMap().get(key);
  }
  
  public abstract V create(V value) ;
  
  public EntityMapTask(EntityMapRegister name) {
    setMap(name);
  }

  HazelcastInstance newHazelcastClient = HazelcastClient.newHazelcastClient();


  protected IMap<K, V> setMap(EntityMapRegister name) {
    switch (name) {
      case BASEENTITY:
        map = newHazelcastClient.getMap(name.getName());
        break;
      case ATTRIBUTE:
        map = newHazelcastClient.getMap(name.getName());
        break;
      case ANSWER:
        map = newHazelcastClient.getMap(name.getName());
        break;
      case QUESTION:
        map = newHazelcastClient.getMap(name.getName());
        break;
      case VALIDATION:
        map = newHazelcastClient.getMap(name.getName());
        break;
      case QBASEMSGMESSAGETEMPLATE:
        map = newHazelcastClient.getMap(name.getName());
      case ASK:
        map = newHazelcastClient.getMap(name.getName());
        break;
    }
    return map;
  }

  
 
  
  // public static void main(String[] args) {
  // Config config = new Config();
  // HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
  // IMap<Integer, BaseEntity> map = hz.getMap("map");
  //
  //
  // // an equal predicate to filter out non ClassA students
  // // EqualPredicate equalPredicate = new EqualPredicate("className", ClassName.ClassA.name());
  //
  // // a comparator which helps to sort in descending order of id field
  // Comparator<Map.Entry> descendingComparator = new Comparator<Map.Entry>() {
  // @Override
  // public int compare(Map.Entry e1, Map.Entry e2) {
  // BaseEntity s1 = (BaseEntity) e1.getValue();
  // BaseEntity s2 = (BaseEntity) e2.getValue();
  // return s2.getId().intValue() - s1.getId().intValue();
  // }
  // };
  //
  // // Comparator<Map.Entry> descendngComparator = (Map.Entry e1, Map.Entry e2) ->{
  // //
  // // };
  //
  // // a predicate which filters out non ClassA students, sort them descending order and fetches 4
  // // students for each page
  // PagingPredicate pagingPredicate = new PagingPredicate(descendingComparator, 4);
  //
  // // expected result:
  // // Page 1 -> Student-18, Student-16, Student-14, Student-12
  // // Page 2 -> Student-10, Student-8, Student-6, Student-4
  // // Page 3 -> Student-2, Student-0
  // Collection<BaseEntity> values = map.values(pagingPredicate);
  // System.out.print("\nPage 1 -> ");
  // for (BaseEntity value : values) {
  // System.out.print(value + ", ");
  // }
  // pagingPredicate.nextPage();
  // values = map.values(pagingPredicate);
  // System.out.print("\nPage 2 -> ");
  // for (BaseEntity value : values) {
  // System.out.print(value + ", ");
  // }
  //
  // pagingPredicate.nextPage();
  // values = map.values(pagingPredicate);
  // System.out.print("\nPage 3 -> ");
  // for (BaseEntity value : values) {
  // System.out.print(value + ", ");
  // }
  //
  // // a predicate which fetches 3 students for each page, natural order (see Student.compareTo()),
  // // does not filter out anything
  // pagingPredicate = new PagingPredicate(3);
  //
  // // since first page is 0, we are requesting the 6th page here
  // // expected result:
  // // Page 6 -> Student-15, Student-16, Student-17
  // pagingPredicate.setPage(5);
  // values = map.values(pagingPredicate);
  // System.out.print("\n\nPage 6 -> ");
  // for (BaseEntity value : values) {
  // System.out.print(value + ", ");
  // }
  //
  //
  //
  // System.out.println();
  // Hazelcast.shutdownAll();
  // }

}
