package org.songdan.spring.mybatis.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;

/**
 * @author: Songdan
 * @create: 2019-07-23 11:29
 **/
@Data
@NoArgsConstructor
public class OriginFee extends Versionable{

    private int fee;

    private int minAmount;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true).configure(JsonParser.Feature.ALLOW_COMMENTS, true)
                .configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true).configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                .configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true).configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true).configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OriginFee originFee = new OriginFee();
        originFee.setFee(5);
        originFee.setMinAmount(10);
        Fee fee = new Fee();
        fee.setOriginFee(originFee);

        System.out.println(objectMapper.writeValueAsString(fee));
    }

}
