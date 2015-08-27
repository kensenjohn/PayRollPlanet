package com.payroll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/27/15.
 */
public class Utility {


    public static <T> T convertJsonToPOJO( String jsonString, Class<T> className ) throws IOException, NoSuchMethodException {
        ObjectMapper mapper = new ObjectMapper();
        T finalBean = mapper.readValue(jsonString,className);
        return finalBean;
    }

    public static <T> String convertPOJOToJson(  T bean ) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonResponse = ow.writeValueAsString( bean );

        return jsonResponse;
    }

    public static enum DEDUCTION_TYPE{
        PRE_TAX, POST_TAX;
    }

    /**
     * Remove all non English Characters in a String.
     * @param inputString
     * @return formatted String with no
     */
    public static String removeNonEnglishCharacters(String inputString){
        StringBuilder inputStringBuilder = new StringBuilder();
        if( inputString!=null && !"".equalsIgnoreCase(inputString) ) {

            for ( Character character : inputString.toCharArray() ){

                if (  Character.isLetter(character ) ){
                    inputStringBuilder.append(character);
                }
            }

        }
        return inputStringBuilder.toString();
    }
}