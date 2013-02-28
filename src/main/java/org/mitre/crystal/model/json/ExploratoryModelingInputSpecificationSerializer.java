/**
 * 
 */
package org.mitre.crystal.model.json;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.mitre.crystal.model.ExploratoryModelingInputSpecification;



/**
 * @author tmlewis
 *
 */
public class ExploratoryModelingInputSpecificationSerializer extends JsonSerializer<ExploratoryModelingInputSpecification>{

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(ExploratoryModelingInputSpecification value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		jgen.writeStringField("name", value.getName());
		jgen.writeStringField("type", value.getType().name().toLowerCase());
		
		Set<String> properties =  value.getType().getPropertyNames();
		
		for (String prop : properties) {
			jgen.writeObjectField(prop, value.getProperty(prop));
		}
		jgen.writeEndObject();
		
	}

}