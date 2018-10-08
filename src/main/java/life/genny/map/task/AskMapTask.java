package life.genny.map.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QDataAskMessage;

public class AskMapTask extends EntityMapTask<Long, Ask> {

  static Comparator<Entry> descendingComparator;

  {
    super.descendingComparator = descendingComparator();
  }

  public AskMapTask() {
    super(Registration.ASK);
  }

  @Override
  public Ask create(Ask value) {
    Long id = getNewId();
    return getMap().put(id, value);
  }

  public Long getNewId() {
    return getMap().keySet().stream().sorted(Collections.reverseOrder()).findFirst().get() + 1;
  }

  public void createAsk(String sourceCode, String questionCode, String targetCode, String uriInfo) {
    QuestionMapTask questionMapResource = new QuestionMapTask();
    BaseEntityMapTask baseEntityMapResource = new BaseEntityMapTask();
    BaseEntity baseTarget = baseEntityMapResource.getByKey(targetCode);
    BaseEntity baseSource = baseEntityMapResource.getByKey(sourceCode);
    Question question = questionMapResource.getByKey(questionCode);
  };

  public List<Ask> fetchAsks() {
    final List<Ask> entitys = new ArrayList(getMap().values());
    return entitys;
  }

  public QDataAskMessage fetchAsksMsg() {
    final List<Ask> entitys = fetchAsks();
    final QDataAskMessage qasks = new QDataAskMessage(entitys.toArray(new Ask[0]));
    return qasks;
  }

  public Ask fetchAskById(final Long id) {
    return fetchById(id);
  }

  @Override
  protected Comparator<Entry> descendingComparator() {
    // TODO Auto-generated method stub
    return null;
  }


  // public static void main(String...strings) {
  // HazelcastServer server = new HazelcastServer();
  //
  // server.startServer();
  //
  // Predicate sqlQuery = new SqlPredicate("id BETWEEN 50 AND 60)");
  //
  // AskMapResource askMapResource = new AskMapResource();
  //
  // askMapResource.getMap().entrySet().forEach(System.out::println);
  //
  // }


  // public Question findQuestionByCode(@NotNull final String code) throws NoResultException {
  // // log.debug("FindQuestionByCode:"+code);
  // List<Question> result = null;
  // final String userRealmStr = getRealm();
  // try {
  // result = (List<Question>) getEntityManager()
  // .createQuery("SELECT a FROM Question a where a.code=:code and a.realm=:realmStr")
  // .setParameter("realmStr", userRealmStr).setParameter("code",
  // code.toUpperCase()).getResultList();
  //
  // } catch (Exception e) {
  // // TODO Auto-generated catch block
  // e.printStackTrace();
  // }
  // if ((result == null) || (result.isEmpty()))
  // return null;
  // return result.get(0);
  // }

}


// public List<Ask> createAsksByQuestion(final Question rootQuestion, final BaseEntity source,
// final BaseEntity target) {
// List<Ask> asks = findAsks(rootQuestion, source, target);
// return asks;
// }

// public List<Ask> createAsksByQuestionCode(final String questionCode, final String sourceCode,
// final String targetCode) {
// Question rootQuestion = findQuestionByCode(questionCode);
// BaseEntity source = findBaseEntityByCode(sourceCode);
// BaseEntity target = findBaseEntityByCode(targetCode);
// return createAsksByQuestion(rootQuestion, source, target);
// }


//
// public Response createAsks(@PathParam("sourceCode") final String sourceCode,
// @PathParam("questionCode") final String questionCode, @PathParam("targetCode") final String
// targetCode,
// @Context final UriInfo uriInfo) {
//
// List<Ask> asks = service.createAsksByQuestionCode(questionCode, sourceCode, targetCode);
// final QDataAskMessage askMsgs = new QDataAskMessage(asks.toArray(new Ask[0]));
//
// String json = JsonUtils.toJson(askMsgs);
// return Response.status(200).entity(json).build();
// }
