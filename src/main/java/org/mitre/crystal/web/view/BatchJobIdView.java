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
import org.mitre.crystal.model.BatchJob;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
/**
 * @author tmlewis
 * Returns a json object with the batchob ID
 */
@Component("batchJobIdView")
public class BatchJobIdView extends AbstractView{


	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {


		final BatchJob batchJob = (BatchJob) model.get("batchJob");
		if(batchJob == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{ //If we have a good batch job
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");

			final PrintWriter writer = response.getWriter();
			final ObjectMapper mapper = new ObjectMapper();
			final JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
			
			jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
			jsonGenerator.writeStartObject();
			//add a batcJob field and put in the ID
			jsonGenerator.writeStringField("batchJob",	 batchJob.getId().toString()); 
			jsonGenerator.writeEndObject();
			jsonGenerator.flush();
		}
	}
}
