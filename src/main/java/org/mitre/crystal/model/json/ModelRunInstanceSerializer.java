/**
 * 
 */
package org.mitre.crystal.model.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.mitre.crystal.model.ModelRunInstance;

/**
 * @author tmlewis
 *
 */
@JsonSerialize(using = ModelRunInstanceSerializer.class)
public class ModelRunInstanceSerializer extends JsonSerializer<ModelRunInstance> {

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(ModelRunInstance arg0, JsonGenerator jgen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		
		jgen.writeStartObject();
		jgen.writeObject(arg0);
		jgen.writeEndObject();
		
	}

}
