package life.genny.map.task;

import java.io.Serializable;
import java.util.Collection;
//import java.util.Comparator;
//import java.util.Map;
//import java.util.Map.Entry;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
//import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.EntryObject;
//import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import life.genny.map.conscript.Registration;
import life.genny.qwandautils.GennySettings;

public abstract class EntityMapTask<K, V>  implements Serializable {


    private IMap<K, V> map;


    public IMap<K, V> getMap() {
        return map;
    }

    public V getByKey(K key) {
        return getMap().get(key);
    }

    public abstract V create(V value);

    public EntityMapTask(Registration name) {
        map = newHazelcastClient.getMap(name.getName());
    }

    protected V fetchById(final Long id) {
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate predicate = e.get("id").equal(id);
        Collection<V> entity = getMap().values(predicate);
        return entity.stream().findFirst().get();
    }


    public final static HazelcastInstance newHazelcastClient;

    static {
        ClientConfig config = new ClientConfig();
        config.getGroupConfig().setName(GennySettings.username);
        config.getGroupConfig().setPassword(GennySettings.username);
        newHazelcastClient = HazelcastClient.newHazelcastClient(config);
    }

}
