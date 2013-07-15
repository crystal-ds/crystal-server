/**
 * 
 */
package org.mitre.crystal.web.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ScoreRunInstance;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author tmlewis
 * 
 */
@Component("csvView")
public class CsvView extends AbstractView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	 * (java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<ModelRunInstance, ScoreRunInstance> m = (Map<ModelRunInstance, ScoreRunInstance>) model
				.get("map");
		if (m == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");

			CSVWriter writer = new CSVWriter(response.getWriter());
			List<String[]> csvFile = new ArrayList<String[]>();

			Set<ModelRunInstance> s = m.keySet();
			Collection<ScoreRunInstance> c = m.values();
			for (ModelRunInstance modelRunInstance : s) {
				for (ScoreRunInstance scoreRunInstance : c) {
					if (modelRunInstance.getId() == scoreRunInstance
							.getMriJobInstanceID()) {
						Set<String> inputs = modelRunInstance.getInputValues()
								.keySet();
						List l = new ArrayList(inputs);
						Collections.sort(l);
						String id = (String) l.get(0);
						String score = scoreRunInstance.getOutputValues().get(
								"Score");
						csvFile.add(new String[] { id, score });

					}
				}
			}
			writer.writeAll(csvFile);
			writer.flush();
			writer.close();
		}
	}

}
