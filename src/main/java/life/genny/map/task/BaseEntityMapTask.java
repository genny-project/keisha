package life.genny.map.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import life.genny.map.conscript.Registration;
import life.genny.qwanda.entity.BaseEntity;


public class BaseEntityMapTask extends EntityMapTask<String, BaseEntity> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


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

    public List<BaseEntity> getBaseEntityInPages() {
        Collection<BaseEntity> values = getMap().values();
        return new ArrayList<BaseEntity>(values);
    }


    protected BaseEntity findBaseEntityByCode(String code, String realm) {
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate equalCode = e.get("code").equal(code).and(e.get("realm").equal(realm));
        Collection<BaseEntity> entity = getMap().values(equalCode);
        return entity.stream().findFirst().get();
    }

}


