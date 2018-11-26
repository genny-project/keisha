package life.genny.map.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.hazelcast.core.MapStore;
import life.genny.map.utils.HibernateUtil;
import life.genny.map.utils.QueryIterable;

/**
 * 
 * EntityMap is extended by other Serializable classes which are intended to be used for fetching
 * from a data store and save them to a Map Hazelcast data structure.
 * 
 * @author helios
 *
 * @param <K>
 * @param <T>
 */
public abstract class EntityMap<K, T> implements MapStore<K, T>, ImplementByType<T> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session = sessionFactory.openSession();

    /**
     * field name of Serializable class
     */
    private final String fieldName;

    /**
     * Query to fetch all keys
     */
    private final Query<?> allKeysQuery;

    /**
     * Class 
     */
    private final Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 
     */
    protected Function<T, Map<K, T>> putOnMapByTypeFunction;

    /**
     * 
     */
    protected BiFunction<T, T, T> cloneFieldIdFunction;

    protected EntityMap(Class<T> entity, String query, String attributeName) {
        this.entityClass = entity;
        this.allKeysQuery = session.createQuery(query);
        this.fieldName = attributeName;
    }

    /**
     * @param keys
     * @return
     */
    private final <T> List<T> loadAllFromType(Collection<K> keys) {
        Query<T> query = (Query<T>) getQueryByParameterType(Collection.class, keys);
        return query.getResultList();
    }

    /**
     * @param kClassParam
     * @param key
     * @return
     */
    private final <K> Query<T> getQueryByParameterType(Class<K> kClassParam, K key) {
        Class<String> st = String.class;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        ParameterExpression<K> parameterExpression = builder.parameter(kClassParam);
        criteriaQuery.where(root.get(fieldName).in(parameterExpression));
        Query<T> query = session.createQuery(criteriaQuery);
        Query<T> parameter = query.setParameter(parameterExpression, key);
        return parameter;
    }


    @Override
    public T load(K key) {
        Collection<K> oneKey = new ArrayList<K>() {
            {
                add(key);
            }
        };
        T singleResult =
            getQueryByParameterType(Collection.class, oneKey).stream().findFirst().orElse(null);

        return singleResult;
    }

    @Override
    public Map<K, T> loadAll(Collection<K> keys) {
        ConcurrentHashMap<K, T> map = new ConcurrentHashMap<K, T>();
        List<T> ents;
        synchronized (this) {
            ents = loadAllFromType(keys);
        }
        for (T ent : ents) {
            map.putAll(putOnMapByTypeFunction.apply(ent));
        }
        return map;
    }

    @Override
    public Iterable<K> loadAllKeys() {
        return new QueryIterable<K>(allKeysQuery);
    }

    @Override
    public void store(K key, T value) {

        Optional<T> optional = Optional.ofNullable(load(key));
        Supplier<Serializable> saveIfNotExist = () -> session.save(value);

        Function<T, Object> mergeIfExist =
            (obj) -> session.merge(cloneFieldIdFunction.apply(obj, value));

        optional.map(mergeIfExist).orElseGet(saveIfNotExist);
        session.beginTransaction().commit();
    }

    @Override
    public void storeAll(Map<K, T> map) {
        map.entrySet().stream().forEach(obj -> store(obj.getKey(), obj.getValue()));
    }

    @Override
    public void delete(K key) {
        Optional<T> ofNullable = Optional.ofNullable(load(key));
        ofNullable.ifPresent(session::delete);
    }

    @Override
    public void deleteAll(Collection<K> keys) {
        Consumer<K> getObjectAndDelete = (key) -> delete(key);
        keys.stream().forEach(getObjectAndDelete);
    }

    @Override
    public BiFunction<T, T, T> cloneFieldId() {
        return (ask1, NULL) -> {
            return ask1;
        };
    };

}
