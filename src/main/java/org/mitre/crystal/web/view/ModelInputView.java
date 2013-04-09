/**
 * 
 */
package org.mitre.crystal.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mitre.crystal.model.ModelSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.sun.tools.internal.ws.wsdl.document.http.HTTPAddress;

/**
 * @author tmlewis
 *
 */
@Component("modelInputView")
public class ModelInputView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ModelSpecification m= (ModelSpecification) model.get("model");
		if (m == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{
		response.getWriter().write(m.toString());
		}
	}

}
