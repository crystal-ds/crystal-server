/**
 * 
 */
package org.mitre.crystal.web.view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.hsqldb.lib.HashMap;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ScoreRunInstance;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tmlewis
 *
 */
@Component("mapView")
public class MapView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest arg1, HttpServletResponse response) throws Exception {
		
		
		Map<ModelRunInstance, ScoreRunInstance> m = (Map<ModelRunInstance, ScoreRunInstance>) arg0.get("map");
		if(m == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		else{
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json");
			
			ObjectMapper mapper = new ObjectMapper();
			Set<ModelRunInstance> s = m.keySet();
			HashMap myMap = new HashMap();
////			
////			
			for (ModelRunInstance modelRunInstance : s) {
				String mriString = mapper.writeValueAsString(modelRunInstance);
				ScoreRunInstance sri = m.get(modelRunInstance);
				myMap.put(mriString, sri);
			}
//			//String json = mapper.writeValueAsString(m);
//			PrintWriter w = response.getWriter();
//			w.print(mapper.writeValueAsString(m));
//			w.flush();
			
			PrintWriter w = response.getWriter();
			JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(w);
			jsonGenerator.writeStartObject();
			jsonGenerator.writeFieldName("map");
			jsonGenerator.writeObject(myMap);
			jsonGenerator.writeEndObject();
			
		}
		
	}

}
