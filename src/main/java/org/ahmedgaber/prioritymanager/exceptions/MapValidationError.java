package org.ahmedgaber.prioritymanager.exceptions;

import org.ahmedgaber.prioritymanager.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class MapValidationError {


    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = getResponseEntity(result);
        if (errorMap != null) return errorMap;
        return null;
    }

    public static ResponseEntity<?> getResponseEntity(BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError m: result.getFieldErrors()) {
                errorMap.put(m.getField(), m.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
