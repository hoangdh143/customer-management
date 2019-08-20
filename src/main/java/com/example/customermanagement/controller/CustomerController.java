package com.example.customermanagement.controller;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Customer>> query(@RequestParam(value = "search") String query) {
        List<Customer> result = null;
        try {
            result= customerService.searchByQuery(query);
        }catch (IllegalArgumentException iae){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }
        return  ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
