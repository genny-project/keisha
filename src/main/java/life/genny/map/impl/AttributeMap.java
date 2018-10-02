package life.genny.map.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import life.genny.qwanda.attribute.Attribute;

public class AttributeMap extends EntityMap<String,Attribute> {

	private final static String query = "select code from Attribute";
	private final static String ATTRIBUTE_NAME = "code";


	{
		super.putOnMapByTypeFunction = putOnMapByType();
		super.cloneFieldIdFunction = cloneFieldId();
	}

	public AttributeMap() {
		super(Attribute.class, query, ATTRIBUTE_NAME);
	}

	@Override
	public Function<Attribute, Map<String, Attribute>> putOnMapByType() {
		return (Attribute attr) -> new HashMap<String, Attribute>() {
			{
				put(attr.getCode(), attr);
			}
		};
	}

	@Override
	public BiFunction<Attribute, Attribute, Attribute> cloneFieldId() {
		return (attr1, attr2) -> {
			attr2.setId(attr1.getId());
			return attr2;
		};
	}
}
