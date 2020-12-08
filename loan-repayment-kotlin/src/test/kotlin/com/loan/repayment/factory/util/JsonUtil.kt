package com.loan.repayment.factory.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import mu.KotlinLogging
import org.springframework.util.ResourceUtils.getFile
import java.io.IOException

/**
 *
 * @author Ramachandra
 */
private val logger = KotlinLogging.logger {}
class JsonUtil {
    /**
     * This method read the json data from the file and deseralizes as Pojo
     *
     * @param fileName Filename of the json
     * @param classz   Class
     * @return Object of the Class
     */
    fun readFileAndGetData(fileName : String , classz : java.lang.Class<*>?) : Any? {
        try {
            return objectMapper.readValue(getFile("classpath:data/$fileName") , classz)
        } catch (e : IOException) {
            logger.debug("Error occurred while fetching the data {}" , e)
        }
        return null
    }//@formatter:off
    //@formatter:on
    /**`
     * This method creates the object mapper with the required modules
     *
     * @return ObjectMapper
     */
    private val objectMapper : ObjectMapper
        get() {
            //@formatter:off
            val mapper : ObjectMapper = ObjectMapper()
                    .registerModule(ParameterNamesModule())
                    .registerModule(Jdk8Module())
                    .registerModule(JavaTimeModule())
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            mapper.setDateFormat(StdDateFormat())
            //@formatter:on
            return mapper
        }
}