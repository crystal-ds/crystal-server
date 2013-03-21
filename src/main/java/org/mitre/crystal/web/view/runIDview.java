/**
 * 
 */
package org.mitre.crystal.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.servlet.view.AbstractView;
/**
 * @author tmlewis
 *
 */
public class runIDview extends AbstractView{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO should this be it's own seriallizer?
		ObjectNode on = new ObjectNode(JsonNodeFactory.instance);
		on.put("batchjob", (Long) model.get("batchJob") );
	
		response.getWriter().write(on.toString());
	}

}
