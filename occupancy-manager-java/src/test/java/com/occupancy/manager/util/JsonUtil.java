package com.occupancy.manager.util;

import static org.springframework.util.ResourceUtils.getFile;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Ramachandra
 *
 */
@Slf4j
public class JsonUtil {
	
	private static final String JSON_DATA_PATH = "classpath:data/";

	/**
	 * This method read the json data from the file and deseralizes as Pojo
	 *
	 * @param fileName Filename of the json
	 * @param classz   Class
	 * @return Object of the Class
	 */
	public ArrayList<Integer> readFileAndGetData(String fileName) {
		try {
			return getObjectMapper().readValue(getFile(JSON_DATA_PATH + fileName),
					new TypeReference<ArrayList<Integer>>() {
					});
		} catch (IOException e) {
			log.error("Error occured while fetching the data {}", e);
		}
		return new ArrayList<>();
	}

	public Object readFileAndGetData(String fileName, Class<?> classz) {
		try {
			return getObjectMapper().readValue(getFile(JSON_DATA_PATH + fileName), classz);
		} catch (IOException e) {
			log.error("Error occured while fetching the data {}", e);
		}
		return null;
	}

	/**
	 * This method creates the object mapper with the required modules
	 *
	 * @return ObjectMapper
	 */
	private ObjectMapper getObjectMapper() {
		//@formatter:off
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat());
        //@formatter:on
		return mapper;
	}
}
