package com.occupancy.manager.util

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import mu.KotlinLogging
import org.springframework.util.ResourceUtils
import java.io.IOException

/**
 *
 * @author Ramachandra
 */
private val logger = KotlinLogging.logger {}

class JsonUtil {
    /**
     * This method read the json data from the file and deserializes as Pojo
     *
     * @param fileName Filename of the json
     * @param classz   Class
     * @return Object of the Class
     */
    fun readFileAndGetData(fileName: String): java.util.ArrayList<Int> {
        try {
            return objectMapper.readValue(ResourceUtils.getFile(
                JSON_DATA_PATH + fileName
            ), object : TypeReference<ArrayList<Int>>() {})
        } catch (e: IOException) {
            logger.error("Error occurred while fetching the data {}", e)
        }
        return ArrayList()
    }

    /**
     * This method read the json data from the file and deserializes as Pojo
     *
     * @param fileName Filename of the json
     * @param classz   Class
     * @return Object of the Class
     */
    fun readFileAndGetData(fileName: String, classz: Class<*>?): Any? {
        try {
            return objectMapper.readValue(ResourceUtils.getFile("classpath:data/$fileName"), classz)
        } catch (e: IOException) {
            logger.debug("Error occurred while fetching the data {}", e)
        }
        return null
    }

    /**`
     * This method creates the object mapper with the required modules
     *
     * @return ObjectMapper
     */
    private val objectMapper: ObjectMapper
        get() {
            //@formatter:off
            val mapper: ObjectMapper = ObjectMapper()
                .registerModule(ParameterNamesModule())
                .registerModule(Jdk8Module())
                .registerModule(JavaTimeModule())
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            mapper.dateFormat = StdDateFormat()
            //@formatter:on
            return mapper
        }

    companion object {
        private const val JSON_DATA_PATH = "classpath:data/"
    }
}