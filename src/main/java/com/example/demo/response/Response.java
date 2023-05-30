package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response 
{
	
	public static ResponseEntity<Object> generateResponse(String message, Boolean status, HttpStatus statuscode, Object responseObj)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        map.put("statuscode", statuscode.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map,statuscode);
    }
}
