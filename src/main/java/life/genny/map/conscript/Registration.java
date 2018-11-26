package life.genny.map.conscript;

import life.genny.map.impl.AnswerMap;
import life.genny.map.impl.AskMap;
import life.genny.map.impl.AttributeMap;
import life.genny.map.impl.BaseEntityMap;
import life.genny.map.impl.QuestionMap;
import life.genny.map.impl.TemplateMap;
import life.genny.map.impl.ValidationMap;

/**
 * Singleton which register the implementation classes of MapStore interface provided by Hazelcast.
 * 
 * @author helios
 *
 */
public enum Registration implements EnlistedMaps {

    /**
     * An Instance of BaseEntityMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    BASEENTITY(BASEENTITY_MAP) {
        @Override
        public BaseEntityMap register() {
            return new BaseEntityMap();
        }
    },

    /**
     * An Instance of AttributeMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    ATTRIBUTE(ATTRIBUTE_MAP) {
        @Override
        public AttributeMap register() {
            return new AttributeMap();
        }
    },

    /**
     * An Instance of AnswerMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    ANSWER(ANSWER_MAP) {
        @Override
        public AnswerMap register() {
            return new AnswerMap();
        }
    },

    /**
     * An Instance of QuestionMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    QUESTION(QUESTION_MAP) {
        @Override
        public QuestionMap register() {
            return new QuestionMap();
        }
    },

    /**
     * An Instance of TemplateMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    QBASEMSGMESSAGETEMPLATE(TEMPLATE_MAP) {
        @Override
        public TemplateMap register() {
            return new TemplateMap();
        }
    },

    /**
     * An Instance of ValidationMap which implements MapStore interface. It has to be also mapped to a
     * store. (e.g. Mysql)
     */
    VALIDATION(VALIDATION_MAP) {
        @Override
        public ValidationMap register() {
            return new ValidationMap();
        }
    },

    /**
     * An Instance of AskMap which implements MapStore interface. It has to be also mapped to a store.
     * (e.g. Mysql)
     */
    ASK(ASK_MAP) {
        @Override
        public AskMap register() {
            return new AskMap();
        }
    };

    /**
     * Field that maintains in memory an instance of Child classes of EntityMap
     */
    // private final EntityMap entityMapImp;

    /**
     * Name of the map use for Hazelcast client or server to retrieve or operate on them.
     */
    final String mapName;

    public String getName() {
        return mapName;
    }

    /**
     * @param name
     * @param entityMap
     */
    Registration(String name) {
        this.mapName = name;
    }

    @Override
    public String toString() {
        return mapName;
    }
}
