package life.genny.map.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import javax.persistence.NoResultException;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
// import org.hibernate.mapping.Set;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.Ask;
import life.genny.qwanda.Question;
import life.genny.qwanda.QuestionQuestion;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.message.QDataAskMessage;

public class AskMapTask extends EntityMapTask<Long, Ask> {

  static Comparator<Entry> descendingComparator;

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

  public QDataAskMessage createAsk(String sourceCode, String questionCode, String targetCode, String uriInfo) {
    QuestionMapTask questionMapResource = new QuestionMapTask();
    BaseEntityMapTask baseEntityMapResource = new BaseEntityMapTask();
    BaseEntity baseTarget = baseEntityMapResource.getByKey(targetCode);
    BaseEntity baseSource = baseEntityMapResource.getByKey(sourceCode);
    Question question = questionMapResource.getByKey(questionCode);
    List<Ask> asks = createAsksByQuestionCode(questionCode, sourceCode, targetCode);
    final QDataAskMessage askMsgs = new QDataAskMessage(asks.toArray(new Ask[0]));
    return askMsgs;
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

  public List<Ask> createAsksByQuestionCode(final String questionCode, final String sourceCode,
      final String targetCode) {
    QuestionMapTask questionMapTask = new QuestionMapTask();
    Question rootQuestion = questionMapTask.findQuestionByCode(questionCode, "genny");// to change
                                                                                      // hard code
                                                                                      // value
                                                                                      // {realm}
    BaseEntity source = findBaseEntityByCode(sourceCode);
    BaseEntity target = findBaseEntityByCode(targetCode);
    return createAsksByQuestion(rootQuestion, source, target);
  }


  public BaseEntity findBaseEntityByCode(final String baseEntityCode) throws NoResultException {
    if ("CMP_EMSGROUP".equals(baseEntityCode)) {
      System.out.println("CMP_EMSGROUP");
    }
    return findBaseEntityByCode(baseEntityCode, false);

  }

  public BaseEntity findBaseEntityByCode(final String baseEntityCode,
      boolean includeEntityAttributes) throws NoResultException {

    BaseEntity result = null;
    final String userRealmStr = null;// getRealm();
    BaseEntity findBaseEntityByCode = null;
    if (includeEntityAttributes) {
      String privacySQL = "";// (inRole("admin")) ? "" : " and ea.privacyFlag=:flag";

      BaseEntityMapTask baseEntityMapTask = new BaseEntityMapTask();
      findBaseEntityByCode =
          baseEntityMapTask.findBaseEntityByCode(baseEntityCode.toUpperCase(), userRealmStr);
      Optional.ofNullable(findBaseEntityByCode);

    } else {
      findBaseEntityByCode.setBaseEntityAttributes(new HashSet<EntityAttribute>() {});
      if ("GRP_ALL_CONTACTS".equalsIgnoreCase(baseEntityCode)) {
        System.out.println("GRP_ADMIN_JOBS");
      }
    }
    return result;

  }

  public List<Ask> createAsksByQuestion(final Question rootQuestion, final BaseEntity source,
      final BaseEntity target) {
    List<Ask> asks = findAsks(rootQuestion, source, target);
    return asks;
  }


  public List<Ask> findAsks(final Question rootQuestion, final BaseEntity source,
      final BaseEntity target) {
    return findAsks(rootQuestion, source, target, false);
  }

  public List<Ask> findAsks(final Question rootQuestion, final BaseEntity source,
      final BaseEntity target, Boolean childQuestionIsMandatory) {
    List<Ask> asks = new ArrayList<Ask>();
    QuestionMapTask questionMapTask = new QuestionMapTask();
    if (rootQuestion.getAttributeCode().equals(Question.QUESTION_GROUP_ATTRIBUTE_CODE)) {
      // Recurse!
      List<QuestionQuestion> qqList =
          new ArrayList<QuestionQuestion>(rootQuestion.getChildQuestions());
      Collections.sort(qqList); // sort by priority
      for (QuestionQuestion qq : qqList) {
        String qCode = qq.getPk().getTargetCode();
        Question childQuestion = questionMapTask.findQuestionByCode(qCode, "genny");
        asks.addAll(findAsks(childQuestion, source, target, qq.getMandatory()));
      }
    } else {
      // This is an actual leaf question, so we can create an ask ...
      Ask ask = null;
      // check if this already exists?
      List<Ask> myAsks = findAsksByQuestion(rootQuestion, source, target);
      if (!((myAsks == null) || (myAsks.isEmpty()))) {
        ask = myAsks.get(0);
        ask.setMandatory(rootQuestion.getMandatory() || childQuestionIsMandatory);
      } else {
        // create one
        Boolean mandatory = rootQuestion.getMandatory() || childQuestionIsMandatory;
        ask = new Ask(rootQuestion, source.getCode(), target.getCode(), mandatory);
        getMap().put(getNewId(), ask); // save
      }
      asks.add(ask);
    }

    return asks;
  }

  public List<Ask> findAsksByQuestion(final Question question, final BaseEntity source,
      final BaseEntity target) {
    return findAsksByQuestionCode(question.getCode(), source.getCode(), target.getCode());
  }

  public List<Ask> findAsksByQuestionCode(final String questionCode, String sourceCode,
      final String targetCode) {
    List<Ask> results = null;
    final String userRealmStr = null; // getRealm();

    EntryObject e = new PredicateBuilder().getEntryObject();
    Predicate equalCode = e.get("questionCode").equal(questionCode)
        .and(e.get("sourceCode").equal(sourceCode)).and(e.get("targetCode").equal(targetCode));
    Collection<Ask> entity = getMap().values(equalCode);
    return new ArrayList(entity);
  }


}

