package com.gschoudhary.open2api.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class JsonMapper {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    public <T> T fromJson(String json, Class<T> clazz) throws Exception {
        // Map JSON to Java Object
        T obj = objectMapper.readValue(json, clazz);

        // Validate the object
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                sb.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
            }
            throw new Exception("Validation errors: \\n " + sb.toString());
        }

        return obj;
    }


    public <T> String toJson(Class<T> object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}

