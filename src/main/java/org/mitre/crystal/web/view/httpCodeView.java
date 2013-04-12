/**
 * 
 */
package org.mitre.crystal.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mitre.crystal.model.BatchJobStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tmlewis
 *
 */
@Component("httpCodeView")
public class HttpCodeView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BatchJobStatus status = (BatchJobStatus) model.get("status");
		switch (status) {
		case UNKNOWN:
			response.setStatus(HttpStatus.NOT_FOUND.value());
			break;
		case RUNNING:
				response.setStatus(HttpStatus.PROCESSING.value());
				break;
		case COMPLETED:
				response.setStatus(HttpStatus.OK.value());
				break;
		case NOT_STARTED:
				response.setStatus(HttpStatus.FOUND.value());
				break;
			
				
		default:
			response.setStatus(HttpStatus.NO_CONTENT.value());
			break;
		}

	}

}
