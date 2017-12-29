package main.java.com.labyrinth.app.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DynamicModelsController {

	// Bean
	@Autowired
	private ServletContext servletContext;
	private static final String modelResourcePath = "/WEB-INF/classes/main/java/com/labyrinth/app/model/";

	// Mapping Java datatypes to HTML data types
	protected static final Map<String, String> typeMap;
	static {
		typeMap = new HashMap<String, String>();
		typeMap.put("java.lang.String", "text");
		typeMap.put("java.util.Date", "date");
		typeMap.put("java.lang.Integer", "number");
	}

	// Populate navigation
	@RequestMapping(value = "/DynamicModels", method = RequestMethod.GET)
	public List<String> getDynamicModels() {

		Set<String> resourcePathSet = new HashSet<String>();
		List<String> dynamicModelList = new ArrayList<String>();

		if (servletContext != null) {
			// get absolute File Paths from Model directory (excludes file name)
			resourcePathSet = (Set<String>) servletContext.getResourcePaths(modelResourcePath);
			// Test resourcePathSet not null
		}

		if (resourcePathSet != null) {
			dynamicModelList = new ArrayList<>();

			for (String resourcePath : resourcePathSet) {
				if (resourcePath.endsWith(".xml")) {
					continue;
				} else {
					// extract model name
					String modelName = resourcePath.replace(modelResourcePath, "").replace(".class", "");
					dynamicModelList.add(modelName);
				}

			}
				// System.out.println(dynamicModelList.toString());
// System.out.println(dynamicModelList.toString());
			// remove internal models
			dynamicModelList.remove("DynamicModels");
			// DevOps demo
			//dynamicModelList.remove("Department");
			
		}

		// List containing model names
		return dynamicModelList;
	}

	// Get Model Structure
	@RequestMapping(value = "/Structure/{modelName}", method = RequestMethod.GET)
	public Map<String, Object> getModelStructure(@PathVariable String modelName) {

		Map<String, Object> modelStructureMap = new HashMap<String, Object>();
		List<Map<String, Object>> modelColumns = new ArrayList<Map<String, Object>>();

		try {
			Class<?> modelClass = Class.forName("main.java.com.labyrinth.app.model." + modelName);
			Field[] modelFields = modelClass.getDeclaredFields();
			// System.out.println(modelFields.toString());

			if (modelFields != null) {
				for (Field field : modelFields) {
					if (field.getName().equals(modelName + "ID")) {
						modelStructureMap.put("primaryKey", modelName + "ID");
						// continue;
					} else {
						Map<String, Object> columnMap = new HashMap<String, Object>();
						Map<String, String> columnMetadataMap = new HashMap<String, String>();

						columnMetadataMap.put("inputType", typeMap.get(field.getType().getName()));
						columnMetadataMap.put("required", "true");
						columnMetadataMap.put("placeholder", "Enter " + field.getName());

						columnMap.put("columnName", field.getName());
						columnMap.put("data", columnMetadataMap);

						modelColumns.add(columnMap);
					}

				}

				// modelStructureMap.put("primaryKey", modelName+"ID");
				modelStructureMap.put("headerColumn", modelFields[1].getName());
				modelStructureMap.put("columns", modelColumns);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelStructureMap;
	}

}
