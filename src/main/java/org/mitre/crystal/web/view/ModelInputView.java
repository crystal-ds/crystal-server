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
import org.mitre.crystal.repository.batchJob.impl.JPABatchJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tmlewis
 *  This view gives a detailed view of a model's inputs
 */
@Component("modelInputView")
public class ModelInputView extends AbstractView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	 * (java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RunnableModel m= (RunnableModel) model.get("model");
		if (m == null){ //If we don't find a model clearly we have a problem
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");
			List<InputNode> l = m.getInputs();


			PrintWriter writer = response.getWriter();
			//Get the object
			ObjectMapper mapper = new ObjectMapper();
			final JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
			jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
			
			jsonGenerator.writeStartObject();
			//write the inputs
			for (Iterator<InputNode> iterator = l.iterator(); iterator.hasNext();) {
				InputNode inputNode = (InputNode) iterator.next();
				jsonGenerator.writeObjectField(inputNode.getName(), inputNode);
			
			}
			jsonGenerator.writeEndObject();
			jsonGenerator.flush();
		}
	}
}
