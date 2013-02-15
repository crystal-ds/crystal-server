package com.crystal.services;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crystal.domain.Input;
import com.crystal.domain.Model;
import com.crystal.domain.varValPair;

/**
 * The Class emeService.
 * 
 */
@Service
public class EMEService {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	 

	private static final Logger logger_c = Logger.getLogger(EMEService.class);
	/**
	 * Substitute for a real database
	 * @return List<Model>
	 */
	private ArrayList<Model> theModels(){
		
		//Model 0 
		varValPair<Integer> parameter0 = new varValPair<Integer>("Integer", 0);
		ArrayList<varValPair> parameters0= new ArrayList<varValPair>();
		parameters0.add(parameter0);
		Input input0 = new Input("INTEGER" , parameters0);
		ArrayList<Input> inputs0= new ArrayList<Input>();
		inputs0.add(input0);
		Model model0 = new Model("model0", "this is model0", 0, inputs0);		
		
		
		//Model 1 
		varValPair<Integer> parameter1 = new varValPair<Integer>("Integer", 1);
		ArrayList<varValPair> parameters1= new ArrayList<varValPair>();
		parameters1.add(parameter1);
		Input input1 = new Input("INTEGER" , parameters1);
		ArrayList<Input> inputs1= new ArrayList<Input>();
		inputs1.add(input1);
		Model model1 = new Model("model1", "this is model1", 1, inputs1);
		
		
		
		//Model 2 
		varValPair<Integer> parameter2 = new varValPair<Integer>("Integer", 2);
		ArrayList<varValPair> parameters2 = new ArrayList<varValPair>();
		parameters2.add(parameter2);
		Input input2 = new Input("INTEGER", parameters2);
		ArrayList<Input> inputs2 = new ArrayList<Input>();
		inputs2.add(input2);
		Model model2 = new Model("model2", "this is model2", 2, inputs2);
		
		ArrayList<Model> models= new ArrayList<Model>();
		models.add(model0);
		models.add(model1);
		models.add(model2);
		
		return models;

	}
	

	/**
	 * Get the model by id.
	 *
	 * @param modelID
	 * 
	 * @return the model by id
	 */
	public Model getModelById(String modelID) {
		int modelidnum = Integer.parseInt(modelID) % 3;
		
		String sql ="SELECT name FROM TEST WHERE id = 1";
		jdbcTemplate.setDataSource(dataSource);
		String name =  	jdbcTemplate.queryForObject(sql, String.class);
		Model returnM = new Model(name, name, 1, null);
		
		return returnM;
		//return theModels().get(modelidnum);
	}



	/**
	 * Gets all models.
	 *
	 * @return all models
	 */
	public List<Model> getAllModels() {

		return theModels();
	}

	/**
	 * Creates the Model.
	 *
	 * @param Model
	 * @return the Model
	 */
	public Model createModel(Model model) {
		//TODO MAKE THIS SAVE
		logger_c.debug("Persisting model in database: " + model.getName());

		

		return model;
	}

	/**
	 * Update Model.
	 *
	 * @param model
	 *            
	 * @return Model
	 */
	public Model updateModel(Model model) {
		//TODO MAKE THIS SAVE
		logger_c.debug("Updating fund in database: " + model.toString());


		return model;
	}

	/**
	 * Delete Model.
	 *
	 * @param Model
	 */
	public void deleteModel(String modelId) {
		int modelidnum = Integer.parseInt(modelId) % 3;
		Model model = theModels().remove(modelidnum % 3);
		logger_c.debug("Deleting fund from database: " + model.getName());

	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}

	
	public JdbcTemplate getJdbcTempalte() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
