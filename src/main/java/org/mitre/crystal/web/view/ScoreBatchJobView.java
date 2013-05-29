/**
 * 
 */
package org.mitre.crystal.web.view;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.impl.DefaultPrettyPrinter;
import org.codehaus.jackson.map.ObjectMapper;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.model.SMBatchJob;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tmlewis
 *
 */
@Component("scoreBatchJobView")
public class ScoreBatchJobView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest arg1, HttpServletResponse response) throws Exception {
		 SMBatchJob sbj = (SMBatchJob) model.get("scoreBatchJob");
		if (sbj == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");
			

			//StringWriter writer = new StringWriter();
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			final JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
			jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
			
			jsonGenerator.writeStartObject();
			
			mapper.writeValue(writer, sbj);
			jsonGenerator.writeEndObject();
			jsonGenerator.flush();
			
//			String inputs = mapper.writeValueAsString(m.getInputs());
//			response.getWriter().write(inputs);
			//response.getWriter().write(writer.toString());
		}

	}

}
