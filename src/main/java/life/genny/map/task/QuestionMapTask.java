package life.genny.map.task;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.Question;

public class QuestionMapTask extends EntityMapTask<String, Question> {


  {
    super.descendingComparator = descendingComparator();
  }

  public QuestionMapTask() {
    super(Registration.QUESTION);
  }

  @Override
  public Question create(Question value) {
    return getMap().put(value.getCode(), value);
  }

  public Question fetchQuestionById(Long id) {
    return fetchById(id);
  }
  
  public List<Question> fetchQuestions() {
    return new ArrayList(getMap().values());
    
  }

  @Override
  protected Comparator<Entry> descendingComparator() {
    // TODO Auto-generated method stub
    return null;
  }

}

// public static void main(String... bM) {



// BaseEntityMapResource baseEntityMapResource = new BaseEntityMapResource();
//
// BaseEntity byKey2 = baseEntityMapResource.getByKey("GRP_SETTINGS");
//
// System.out.println(byKey2);
//
// AnswerMapResource answerMapResource = new AnswerMapResource();
//
// Answer byKey = answerMapResource.getByKey(2L);
//
// System.out.println(byKey);

// answerMapResource.getAnswerMap().keySet().stream().sorted(Collections.reverseOrder())
// .peek(System.out::println).findFirst().get();

// answerMapResource.saveBulk();
// AskMapResource askMapResource = new AskMapResource();
// askMapResource.getMap().entrySet().stream()
// .forEach(en -> System.out.println(en.getValue().getQuestion().getAttribute()));

// IMap<String, BaseEntity> map = new BaseEntityMapResource();


// System.out.println(map.size());
// System.out.println(map.size());
// map.entrySet().stream().forEach(System.out::println);


// HazelcastInstance newHazelcastClient = HazelcastClient.newHazelcastClient();
// newHazelcastClient.getMap(BASEENTITY);
// HazelcastInstance node = Hazelcast.newHazelcastInstance(config);
// IMap<String, BaseEntity> besMap = node.getMap(BASEENTITY);
// IMap<String, Validation> valMap = node.getMap(VALIDATION);
// IMap<String, QBaseMSGMessageTemplate> temMap = node.getMap(TEMPLATE);
// IMap<String, Attribute> attrMap = node.getMap(ATTRIBUTE);
// IMap<String, Question> queMap = node.getMap(QUESTION);
// IMap<String, Answer> answMap = node.getMap(ANSWER);


// node.getDistributedObjects().forEach(out::println);

// System.out.println(besMap.entrySet().stream().count());
// System.out.println(valMap.entrySet().stream().count());
// System.out.println(temMap.entrySet().stream().count());
// System.out.println(attrMap.entrySet().stream().count());
// System.out.println(queMap.entrySet().stream().count());
// System.out.println(answMap.size());

// BaseEntity baseEntity2 = besMap.get("PER_OKOKPOL");
//
//
// Attribute attribute2 = attrMap.get("CAP_ADD_USER");
//
//
// try {
// baseEntity2.addAttribute(attribute2, 1.0);
// } catch (BadDataException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// BaseEntity baseEntity3 = new BaseEntity("PER_BDO", "Add addd");
// BaseEntity baseEntity4 = new BaseEntity("PER_BDI", "idd iddd");
//
//// Map<String, BaseEntity> mapb = new HashMap<String,BaseEntity>();
//
// besMap.put("PER_OKOKPOL", baseEntity2 );
//
//// besMap.putAll(mapb);
//
//// besMap.remove("PER_OKOKPOL");
//
// besMap.entrySet().forEach(System.out::println);
// Predicate sqlQuery = new SqlPredicate("id BETWEEN 50 AND 60)");
// Predicate sqlQuery2 = new SqlPredicate("code=PER_OKOKPOL");
//// besMap.remove("PER_BD");
// besMap.values(sqlQuery2).stream().forEach(be->
// System.out.println(be.getBaseEntityAttributes()));

// answMap.entrySet().stream().forEach(ans -> System.out.println(ans.getValue().getId()));

// }

// private static final <A> Query<AnswerLink> getQueryByParameterType(Class<A> parameterClass, A
// value) {
// CriteriaBuilder builder = session.getCriteriaBuilder();
// CriteriaQuery<AnswerLink> criteriaQuery = builder.createQuery(AnswerLink.class);
// Root<AnswerLink> root = criteriaQuery.from(AnswerLink.class);
// ParameterExpression<A> parameterExpression = builder.parameter(parameterClass);
// criteriaQuery.where(root.get("pk").get("source").in(parameterExpression));
// Query<AnswerLink> query = session.createQuery(criteriaQuery);
// Query<AnswerLink> parameter = query.setParameter(parameterExpression, value);
// return parameter;
// }
// public static void main(String...strings) {
// BaseEntity baseEntity2 = new BaseEntity("PER_USER1");
// baseEntity2.setId(236L);
// Query<AnswerLink> queryByParameterType = getQueryByParameterType(BaseEntity.class,
// baseEntity2);
// List<AnswerLink> resultList = queryByParameterType.getResultList();
//
//
//
//// resultList.stream().forEach(System.out::println);
// }

// private static Config createNewConfig() throws SQLException {
//
// Config config = new Config();
//// config.getGroupConfig().setName(GennySettings.username);
//// config.getGroupConfig().setPassword(GennySettings.username);
//
// MapStoreConfig mapBaseStoreConfig = new MapStoreConfig();
// MapStoreConfig mapValStoreConfig = new MapStoreConfig();
// MapStoreConfig mapTemStoreConfig = new MapStoreConfig();
// MapStoreConfig mapAttrStoreConfig = new MapStoreConfig();
// MapStoreConfig mapQueStoreConfig = new MapStoreConfig();
// MapStoreConfig mapAnswStoreConfig = new MapStoreConfig();
//
// BaseEntityMap bMap = new BaseEntityMap();
// ValidationMap valMap = new ValidationMap();
// TemplateMap temMap = new TemplateMap();
// AttributeMap attrMap = new AttributeMap();
// QuestionMap queMap = new QuestionMap();
// AnswerMap answMap = new AnswerMap();
//
// mapBaseStoreConfig.setImplementation(bMap);
// mapValStoreConfig.setImplementation(valMap);
// mapTemStoreConfig.setImplementation(temMap);
// mapAttrStoreConfig.setImplementation(attrMap);
// mapQueStoreConfig.setImplementation(queMap);
// mapAnswStoreConfig.setImplementation(answMap);
//
// mapBaseStoreConfig.setWriteDelaySeconds(10);
//
// MapConfig mapBaseConfig = config.getMapConfig(MapName.BASEENTITY.getName());
// MapConfig mapValConfig = config.getMapConfig(MapName.VALIDATION.getName());
// MapConfig mapTmpConfig = config.getMapConfig(MapName.TEMPLATE.getName());
// MapConfig mapAttrConfig = config.getMapConfig(MapName.ATTRIBUTE.getName());
// MapConfig mapQueConfig = config.getMapConfig(MapName.QUESTION.getName());
// MapConfig mapAnswConfig = config.getMapConfig(MapName.ANSWER.getName());
//
// mapBaseConfig.setMapStoreConfig(mapBaseStoreConfig);
// mapValConfig.setMapStoreConfig(mapValStoreConfig);
// mapTmpConfig.setMapStoreConfig(mapTemStoreConfig);
// mapAttrConfig.setMapStoreConfig(mapAttrStoreConfig);
// mapQueConfig.setMapStoreConfig(mapQueStoreConfig);
// mapAnswConfig.setMapStoreConfig(mapAnswStoreConfig);
//
// return config;
// }

// @Override
// public void start() {
// EntryAddedListener<Integer, Integer> listAddedEntry = System.out::println;
//
// Config cfg = new Config();
// cfg.getGroupConfig().setName(GennySettings.username);
// cfg.getGroupConfig().setPassword(GennySettings.username);
//
// HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
// IMap map = instance.getMap(GennySettings.mainrealm);
// MapListenerAdapter<Object, Object> ts = new MapListenerAdapter<Object, Object>();
// map.addEntryListener(listAddedEntry, false);
//
// System.out.println("hello");
// }

// public static void main(String... strings) {
//// vertx.executeBlocking(block->{
// String mapName = ServiceVerticle.class.getCanonicalName();
//
// Config config = null;
// try {
// config = createNewConfig();
// } catch (SQLException e) {
//// TODO Auto-generated catch block
// e.printStackTrace();
// }
// HazelcastInstance node = Hazelcast.newHazelcastInstance(config);
//
// IMap<Long, BaseEntity> map = node.getMap("andres");
//// IMap<Long, AnswerLink> map2 = node.getMap("byron");
//// map.evictAll();
//
// node.getDistributedObjects().forEach(out::println);
// map.loadAll(true);
//
// System.out.println("hehehehe");
//// map.entrySet().stream().forEach(System.out::println);
//// map2.entrySet().stream().forEach(System.out::println);
// System.out.printf("\n\n\n\n%s", map.size());
//
//// }, res ->{});
// }

// @Override
// @Override
// public static void main(String...bM) {
//// vertx.executeBlocking(block -> {
// String mapName = ServiceVerticle.class.getCanonicalName();
//
// Config config = null;
// try {
// config = createNewConfig();
// } catch (SQLException e) {
//// TODO Auto-generated catch block
// e.printStackTrace();
// }
// HazelcastInstance node = Hazelcast.newHazelcastInstance(config);
// IMap<Long, BaseEntity> map = node.getMap("andres");
//// IMap<Long, AnswerLink> map2 = node.getMap("byron");
//// map.evictAll();
// node.getDistributedObjects().forEach(out::println);
//// map.loadAll(keys, replaceExistingValues);
//// map.loadAll(true);
//// map.loadAll(ids,true);
//// map.entrySet().stream().forEach(System.out::println);
//// map2.entrySet().stream().forEach(System.out::println);
// map.entrySet().forEach(System.out::println);
// System.out.printf("\n\n\n\n%s", map.size());
//// }, res -> {
//// });
// }
// private static Config createNewConfig() throws SQLException {
// BaseEntityMap2 bMap = new BaseEntityMap2();
//
// MapStoreConfig mapBaseStoreConfig = new MapStoreConfig();
// mapBaseStoreConfig.setImplementation(bMap);
//
//// mapAttrStoreConfig.setImplementation(attMap);
//
//// mapStoreConfig.setImplementation(aLMap);
//// mapStoreConfig.setImplementation(askMap);
//// mapStoreConfig.setImplementation(attMap);
//
// mapBaseStoreConfig.setWriteDelaySeconds(10);
// Config config = new Config();
//
// config.getGroupConfig().setName(GennySettings.username);
// config.getGroupConfig().setPassword(GennySettings.username);
//
// MapConfig mapBaseConfig = config.getMapConfig("andres");
//// MapConfig mapAttConfig = config.getMapConfig("byron");
//
// mapBaseConfig.setMapStoreConfig(mapBaseStoreConfig);
//// mapAttConfig.setMapStoreConfig(mapAttrStoreConfig);
//
// return config;
// }

// @Override
// public void start() {
// MapStoreConfig mapStoreConfig = new MapStoreConfig();
// BaseEntityMap bMap = new BaseEntityMap();
// mapStoreConfig.setImplementation(bMap);
// mapStoreConfig.setWriteDelaySeconds(0);
//
// int numberOfEntriesToAdd = 1000;
//
// Predicate sqlQuery = new SqlPredicate("id BETWEEN 50 AND 60)");
// Predicate criteriaQuery = Predicates.and(Predicates.equal("active", true),
// Predicates.between("age", 18, 21));
//
//
// ClientConfig cfg = new ClientConfig();
//// cfg.getMapConfig(mapName);
//
// cfg.getGroupConfig().setName("GENNY");
// cfg.getGroupConfig().setPassword("GENNY");
// cfg.getNetworkConfig().addAddress("cache");
// cfg.getNetworkConfig().setRedoOperation(true);
//
// HazelcastInstance client = HazelcastClient.newHazelcastClient(cfg);
//
// IMap mapBaseEntitys = client.getMap("baseentitys"); // To fix
//
// mapBaseEntitys.loadAll(true);
// System.out.println(mapBaseEntitys.size());
//
// }

// private static Query allKeysQuery = session.createQuery("select id from BaseEntity");

// public static void main(String... stss) {
//// Set<Long> ids = new HashSet<Long>();
//// ids.add(2L);
//// ids.add(3L);
////// ids.add(7L);ids.add(8L);ids.add(6L);ids.add(9L);
////// ids.add(5L);ids.add(3L);ids.add(1L);ids.add(4L);
//// CriteriaBuilder builder = session.getCriteriaBuilder();
//// CriteriaQuery<BaseEntity> criteriaQuery = builder.createQuery(BaseEntity.class);
//// Root<BaseEntity> root = criteriaQuery.from(BaseEntity.class);
//// ParameterExpression<Collection> parameterExpression = builder.parameter(Collection.class);
//// criteriaQuery.where(root.get("id").in(parameterExpression));
//// Query<BaseEntity> query = session.createQuery(criteriaQuery);
//// Query<BaseEntity> parameter = query.setParameter(parameterExpression, ids);
//// List<BaseEntity> bes = parameter.getResultList();
//// bes.stream().forEach(System.out::println);
//// vertx.executeBlocking(block -> {
// String mapName = ServiceVerticle.class.getCanonicalName();
//
// Config config = null;
// try {
// config = createNewConfig();
// } catch (SQLException e) {
//// TODO Auto-generated catch block
// e.printStackTrace();
// }
// HazelcastInstance node = Hazelcast.newHazelcastInstance(config);
// IMap<Long, BaseEntity> map = node.getMap("andres");
//// IMap<Long, AnswerLink> map2 = node.getMap("byron");
//// map.evictAll();
// node.getDistributedObjects().forEach(out::println);
//// map.loadAll(keys, replaceExistingValues);
//
// QueryIterable<Long> queryIterable = new QueryIterable<Long>(allKeysQuery);
//// queryIterable.iterator()
// Set<Long> ids = new HashSet<Long>();
// for (Long s : queryIterable)
// ids.add(s);
//
// System.out.println("zzz1111zzzzzzzzzzzz ");
//
// map.loadAll(ids,true);
//
// System.out.println("zzzzzzzzzzzzzzz ");
// map.entrySet().forEach(System.out::println);
//// map.loadAll(ids,true);
//// map.entrySet().stream().forEach(System.out::println);
//// map2.entrySet().stream().forEach(System.out::println);
//// System.out.printf("\n\n\n\n%s", map.size());
//// }, res -> {
//// });
// }

// public static void main(String... stss) {
// QueryIterable<Long> queryIterable = new QueryIterable<Long>(allKeysQuery);
//// queryIterable.iterator()
// Set<Long> ids = new HashSet<Long>();
// for(Long s : queryIterable)
// ids.add(s);
//// ids.add(7L);ids.add(8L);ids.add(6L);ids.add(9L);
//// ids.add(5L);ids.add(3L);ids.add(1L);ids.add(4L);
// CriteriaBuilder builder = session.getCriteriaBuilder();
// CriteriaQuery<BaseEntity> criteriaQuery = builder.createQuery(BaseEntity.class);
// Root<BaseEntity> root = criteriaQuery.from(BaseEntity.class);
// ParameterExpression<Collection> parameterExpression = builder.parameter(Collection.class);
// criteriaQuery.where(root.get("id").in(parameterExpression));
// Query<BaseEntity> query = session.createQuery(criteriaQuery);
// Query<BaseEntity> parameter = query.setParameter(parameterExpression, ids);
// List<BaseEntity> bes = parameter.getResultList();
// bes.stream().forEach(System.out::println);
// }

// public static String attributeKey(Attribute obj) {
// return new String().concat(obj.getCode());
// }
//
// public static String baseEntityKey(BaseEntity obj) {
// return new String().concat(obj.getCode());
// }
//
// public static String answerLinkKey(AnswerLink obj) {
// return new
// String().concat(obj.getAttributeCode()).concat(obj.getSourceCode()).concat(obj.getTargetCode());
// }
//
// public static String answerKey(Answer obj) {
// return new
// String().concat(obj.getAttributeCode()).concat(obj.getSourceCode()).concat(obj.getTargetCode());
// }
//
// public static String askKey(Ask obj) {
// return new
// String().concat(obj.getAttributeCode()).concat(obj.getSourceCode()).concat(obj.getTargetCode());
// }
//
// public static String entityAttributeKey(EntityAttribute obj) {
// return new String().concat(obj.getBaseEntityCode()).concat(obj.getAttributeCode());
// }
//
// public static String entityEntityKey(EntityEntity obj) {
// return new
// String().concat(obj.getPk().getAttribute().getCode()).concat(obj.getPk().getTargetCode())
// .concat(obj.getPk().getSource().getCode());
// }
//
// public static String questionKey(Question obj) {
// return new String().concat(obj.getCode());
// }
//
// public static String questionQuestionKey(QuestionQuestion obj) {
// return new String().concat(obj.getPk().getSourceCode()).concat(obj.getPk().getTargetCode());
// }
//
// public static String validationKey(Validation obj) {
// return new String().concat(obj.getCode());
// }
//
// public static String qBaseMSGMessageTemplateKey(QBaseMSGMessageTemplate obj) {
// return new String().concat(obj.getCode());
// }
//// getAllFromTable(QBaseMSGMessageTemplate.class);
//
// public static String keyMap(Object obj) {
// if (obj instanceof BaseEntity)
// return baseEntityKey((BaseEntity) obj);
// else if (obj instanceof Attribute)
// return attributeKey((Attribute) obj);
// else if (obj instanceof AnswerLink)
// return answerLinkKey((AnswerLink) obj);
// else if (obj instanceof Answer)
// return answerKey((Answer) obj);
// else if (obj instanceof Ask)
// return askKey((Ask) obj);
// else if (obj instanceof EntityAttribute)
// return entityAttributeKey((EntityAttribute) obj);
// else if (obj instanceof EntityEntity)
// return entityEntityKey((EntityEntity) obj);
// else if (obj instanceof Question)
// return questionKey((Question) obj);
// else if (obj instanceof QuestionQuestion)
// return questionQuestionKey((QuestionQuestion) obj);
// return null;
// }

// public static void main(String... strings) {
// Config cfg = new Config();
// cfg.getGroupConfig().setName("GENNY");
// cfg.getGroupConfig().setPassword("GENNY");
//
// HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
//
// IMap<String, BaseEntity> beMap = instance.getMap("BaseEntity");
// IMap<String, Attribute> attrMap = instance.getMap("Attribute");
//
//// Optional<Map<String, BaseEntity>> reduce = HelperStore.dataMap(BaseEntity.class);
//
//// if (reduce.isPresent()) {
//// beMap.putAll(reduce.get());
//// beMap.entrySet().stream().forEach(System.out::println);
//// } else
//// System.out.println("nothing there");
//
//// Optional<Map<String, Attribute>> reduce2 = HelperStore.dataMap(Attribute.class);
//
//// if (reduce2.isPresent()) {
//// attrMap.putAll(reduce2.get());
//// attrMap.entrySet().stream().forEach(System.out::println);
//// } else
//// System.out.println("nothing there");
// Stream map = clazzes.stream().map(HelperStore::dataMap);
// }

// final static List<Class> clazzes = new ArrayList<Class>() {
// {
// add(AnswerLink.class);
// add(Answer.class);
// add(Ask.class);
// add(EntityAttribute.class);
// add(EntityEntity.class);
// add(Question.class);
// add(QuestionQuestion.class);
// add(Validation.class);
// add(QBaseMSGMessageTemplate.class);
// }
// };

// public void classRegister

// }
