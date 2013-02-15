package com.crystal.webservices.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.crystal.domain.Model;
import com.crystal.domain.ScoringModel;
import com.crystal.services.EMEService;
import com.crystal.services.SMEService;

/**
 * ModelController class will expose a series of RESTful endpoints
 */
@Controller
public class RestController {

	@Autowired
	private EMEService EMEService_i;
	
	@Autowired
	private SMEService SMEService_i;

	@Autowired
	private View jsonView_i;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	private static final Logger logger_c = Logger.getLogger(RestController.class);

	/*****************************************SME************************************/
	/**
	 * Gets a model by modelid.
	 *
	 * @param modelid
	 * @return the model
	 */
	@RequestMapping(value = "/sme/models/{modelId}", method = RequestMethod.GET)
	public ModelAndView getScoringModel(@PathVariable("modelId") String modelId_p) {
		ScoringModel model = null;

		/* validate model Id parameter */
		if (isEmpty(modelId_p)) {
			String sMessage = "Error invoking getScoringModel - Invalid model Id parameter";
			return createErrorResponse(sMessage);
		}

		try {
			model = SMEService_i.getScoringModelById(modelId_p);
		} catch (Exception e) {
			String sMessage = "Error invoking getScoringModel. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returning Scoring Model: " + model.getName());
		return new ModelAndView(jsonView_i, DATA_FIELD, model);
	}

	/**
	 * Gets all models.
	 *
	 * @return the models
	 */
	@RequestMapping(value = "/sme/models/", method = RequestMethod.GET)
	public ModelAndView getScoringModels() {
		List<ScoringModel> models = null;

		try {
			models = SMEService_i.getAllScoringModels();
		} catch (Exception e) {
			String sMessage = "Error getting all models. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returning Scoring Models: " + models.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, models);
	}

	/**
	 * Creates a new model.
	 *
	 * @param model
	 * @return the model and view
	 */
	@RequestMapping(value = { "/sme/model/{modelId}" }, method = { RequestMethod.POST })
	public ModelAndView createScoringModel(@RequestBody ScoringModel modelId,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		ScoringModel createdModel;
		logger_c.debug("Creating Scoring Model: " + modelId.toString());

		try {
			createdModel = SMEService_i.createScoringModel(modelId);
		} catch (Exception e) {
			String sMessage = "Error creating new scoring model. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response code */
		httpResponse_p.setStatus(HttpStatus.CREATED.value());

		/* set location of created resource */
		httpResponse_p.setHeader("Location", request_p.getContextPath() + "/sme/model/" + modelId.getID());

		/**
		 * Return the view
		 */
		return new ModelAndView(jsonView_i, DATA_FIELD, createdModel);
	}

	/**
	 * Updates Scoring Model with given id.
	 *
	 * @param model_p
	 *            the model_p
	 * @return the model and view
	 */
	@RequestMapping(value = { "/sme/model/{modelId}" }, method = { RequestMethod.PUT })
	public ModelAndView updateScoringModel(@RequestBody ScoringModel model_p, @PathVariable("modelId") String modelId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Updating Scoring Model: " + model_p.toString());

		/* validate Model Id parameter */
		if (isEmpty(modelId_p)){
			String sMessage = "Error updating Scoring Model - Invalid Id parameter";
			return createErrorResponse(sMessage);
		}

		ScoringModel model = null;

		try {
			model = SMEService_i.updateScoringModel(model_p);
		} catch (Exception e) {
			String sMessage = "Error updating Scoring Model. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, model);
	}

	/**
	 * Deletes the Scoring Model with the given id.
	 *
	 * @param modelId_p
	 *            the model id_p
	 * @return the model and view
	 */
	@RequestMapping(value = "/sme/models/{modelId}", method = RequestMethod.DELETE)
	public ModelAndView removeScoringModel(@PathVariable("modelId") String modelId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Deleting Scoring Model Id: " + modelId_p.toString());

		/* validate model Id parameter */
		if (isEmpty(modelId_p)){
			String sMessage = "Error deleting scoring model - Invalid model Id parameter";
			return createErrorResponse(sMessage);
		}

		try {
			SMEService_i.deleteScoringModel(modelId_p);
		} catch (Exception e) {
			String sMessage = "Error invoking deleteScoringModel. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, null);
	}

	
	
	/*****************************************EME************************************/
	/**
	 * Gets a model by modelid.
	 *
	 * @param modelid
	 * @return the model
	 */
	@RequestMapping(value = "/eme/models/{modelId}", method = RequestMethod.GET)
	public ModelAndView getModel(@PathVariable("modelId") String modelId_p) {
		Model model = null;

		/* validate model Id parameter */
		if (isEmpty(modelId_p)) {
			String sMessage = "Error invoking getModel - Invalid model Id parameter";
			return createErrorResponse(sMessage);
		}

		try {
			model = EMEService_i.getModelById(modelId_p);
		} catch (Exception e) {
			String sMessage = "Error invoking getModel. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returning Model: " + model.getName());
		return new ModelAndView(jsonView_i, DATA_FIELD, model);
	}

	/**
	 * Gets all models.
	 *
	 * @return the models
	 */
	@RequestMapping(value = "/eme/models/", method = RequestMethod.GET)
	public ModelAndView getModels() {
		List<Model> models = null;

		try {
			models = EMEService_i.getAllModels();
		} catch (Exception e) {
			String sMessage = "Error getting all models. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		logger_c.debug("Returning Models: " + models.toString());
		return new ModelAndView(jsonView_i, DATA_FIELD, models);
	}

	/**
	 * Creates a new model.
	 *
	 * @param model
	 * @return the model and view
	 */
	@RequestMapping(value = { "/eme/model/{modelId}" }, method = { RequestMethod.POST })
	public ModelAndView createModel(@RequestBody Model modelId,
			HttpServletResponse httpResponse_p, WebRequest request_p) {

		Model createdModel;
		logger_c.debug("Creating Model: " + modelId.toString());

		try {
			createdModel = EMEService_i.createModel(modelId);
		} catch (Exception e) {
			String sMessage = "Error creating new model. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		/* set HTTP response code */
		httpResponse_p.setStatus(HttpStatus.CREATED.value());

		/* set location of created resource */
		httpResponse_p.setHeader("Location", request_p.getContextPath() + "/eme/model/" + modelId.getID());

		/**
		 * Return the view
		 */
		return new ModelAndView(jsonView_i, DATA_FIELD, createdModel);
	}

	/**
	 * Updates Model with given Model id.
	 *
	 * @param model_p
	 *            the model_p
	 * @return the model and view
	 */
	@RequestMapping(value = { "/eme/model/{modelId}" }, method = { RequestMethod.PUT })
	public ModelAndView updateModel(@RequestBody Model model_p, @PathVariable("modelId") String modelId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Updating Model: " + model_p.toString());

		/* validate Model Id parameter */
		if (isEmpty(modelId_p)){
			String sMessage = "Error updating Model - Invalid Model Id parameter";
			return createErrorResponse(sMessage);
		}

		Model model = null;

		try {
			model = EMEService_i.updateModel(model_p);
		} catch (Exception e) {
			String sMessage = "Error updating Model. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, model);
	}

	/**
	 * Deletes the Model with the given Model id.
	 *
	 * @param modelId_p
	 *            the model id_p
	 * @return the model and view
	 */
	@RequestMapping(value = "/eme/models/{modelId}", method = RequestMethod.DELETE)
	public ModelAndView removeModel(@PathVariable("modelId") String modelId_p,
								   HttpServletResponse httpResponse_p) {

		logger_c.debug("Deleting Model Id: " + modelId_p.toString());

		/* validate model Id parameter */
		if (isEmpty(modelId_p) || modelId_p.length() < 5) {
			String sMessage = "Error deleting model - Invalid model Id parameter";
			return createErrorResponse(sMessage);
		}

		try {
			EMEService_i.deleteModel(modelId_p);
		} catch (Exception e) {
			String sMessage = "Error invoking getModels. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		}

		httpResponse_p.setStatus(HttpStatus.OK.value());
		return new ModelAndView(jsonView_i, DATA_FIELD, null);
	}

	public static boolean isEmpty(String s_p) {
		return (null == s_p) || s_p.trim().length() == 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Create an error REST response.
	 *
	 * @param sMessage
	 *            the s message
	 * @return the model and view
	 */
	private ModelAndView createErrorResponse(String sMessage) {
		return new ModelAndView(jsonView_i, ERROR_FIELD, sMessage);
	}

	/**
	 * Injector methods.
	 *
	 * @param modelService_p
	 *            the new model service
	 */
	public void setModelService(EMEService EMEService_p) {
		EMEService_i = EMEService_p;
	}
	
	/**
	 * Injector methods.
	 *
	 * @param modelService_p
	 *            the new model service
	 */
	public void setScoringModelService(SMEService SMEService_p) {
		SMEService_i = SMEService_p;
	}

	/**
	 * Injector methods.
	 *
	 * @param view
	 *            the new json view
	 */
	public void setJsonView(View view) {
		jsonView_i = view;
	}

}
