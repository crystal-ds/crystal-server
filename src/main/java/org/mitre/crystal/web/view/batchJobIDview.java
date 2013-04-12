/**
 * 
 */
package org.mitre.crystal.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.mitre.crystal.model.BatchJob;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
/**
 * @author tmlewis
 *
 */
@Component("batchJobIDView")
public class BatchJobIDview extends AbstractView{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ObjectNode on = new ObjectNode(JsonNodeFactory.instance);
		BatchJob batchJob = (BatchJob) model.get("batchJob");
		on.put("batchjob",  batchJob.getId() );
	
		response.getWriter().write(on.toString());
	}

}
