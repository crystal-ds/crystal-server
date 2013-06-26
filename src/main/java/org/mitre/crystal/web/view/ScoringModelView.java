/**
 * 
 */
package org.mitre.crystal.web.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.impl.DefaultPrettyPrinter;
import org.codehaus.jackson.map.ObjectMapper;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.model.ScoringModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tmlewis
 *
 */
@Component("scoringModelView")
public class ScoringModelView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ScoringModel m= (ScoringModel) model.get("model");
		if (m == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");
			//List<InputNode> l = m.getInputs();

			//StringWriter writer = new StringWriter();
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
			jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
			
			jsonGenerator.writeStartObject();
			jsonGenerator.writeObjectField(m.getId().toString(), m);
			//jsonGenerator.writeObject(m);
			jsonGenerator.writeEndObject();
			jsonGenerator.flush();
		}

	}

}
