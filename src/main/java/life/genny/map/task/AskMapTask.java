package life.genny.map.task;

import java.util.Collections;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.SqlPredicate;
import life.genny.map.config.ApplicationServer;
import life.genny.map.config.EntityMapRegister;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.entity.BaseEntity;

public class AskMapTask extends EntityMapTask<Long, Ask> {


  public AskMapTask() {
    super(EntityMapRegister.ASK);
  }

  @Override
  public Ask create(Ask value) {
    Long id = getNewId();
    return getMap().put(id, value);
  }
  
  public Long getNewId() {
    return getMap().keySet().stream()
        .sorted(Collections.reverseOrder())
        .findFirst().get() + 1;
  }
  
  public void createAsk(String sourceCode, String questionCode, String targetCode, String uriInfo) {
    QuestionMapTask questionMapResource = new QuestionMapTask();
    BaseEntityMapTask baseEntityMapResource = new BaseEntityMapTask();
    BaseEntity baseTarget = baseEntityMapResource.getByKey(targetCode);
    BaseEntity baseSource = baseEntityMapResource.getByKey(sourceCode);
    Question question = questionMapResource.getByKey(questionCode);
  };
  
//  public static void main(String...strings) {
//    HazelcastServer server = new HazelcastServer();
//
//    server.startServer();
//    
//     Predicate sqlQuery = new SqlPredicate("id BETWEEN 50 AND 60)");
//
//    AskMapResource askMapResource = new AskMapResource();
//    
//    askMapResource.getMap().entrySet().forEach(System.out::println);  
//    
//  }
  
  
//  public Question findQuestionByCode(@NotNull final String code) throws NoResultException {
//    // log.debug("FindQuestionByCode:"+code);
//    List<Question> result = null;
//    final String userRealmStr = getRealm();
//    try {
//        result = (List<Question>) getEntityManager()
//                .createQuery("SELECT a FROM Question a where a.code=:code and a.realm=:realmStr")
//                .setParameter("realmStr", userRealmStr).setParameter("code", code.toUpperCase()).getResultList();
//
//    } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//    if ((result == null) || (result.isEmpty()))
//        return null;
//    return result.get(0);
//}
  
}


//public List<Ask> createAsksByQuestion(final Question rootQuestion, final BaseEntity source,
//    final BaseEntity target) {
//List<Ask> asks = findAsks(rootQuestion, source, target);
//return asks;
//}

//public List<Ask> createAsksByQuestionCode(final String questionCode, final String sourceCode,
//    final String targetCode) {
//Question rootQuestion = findQuestionByCode(questionCode);
//BaseEntity source = findBaseEntityByCode(sourceCode);
//BaseEntity target = findBaseEntityByCode(targetCode);
//return createAsksByQuestion(rootQuestion, source, target);
//}


//
//public Response createAsks(@PathParam("sourceCode") final String sourceCode,
//    @PathParam("questionCode") final String questionCode, @PathParam("targetCode") final String targetCode,
//    @Context final UriInfo uriInfo) {
//
//List<Ask> asks = service.createAsksByQuestionCode(questionCode, sourceCode, targetCode);
//final QDataAskMessage askMsgs = new QDataAskMessage(asks.toArray(new Ask[0]));
//
//String json = JsonUtils.toJson(askMsgs);
//return Response.status(200).entity(json).build();
//}