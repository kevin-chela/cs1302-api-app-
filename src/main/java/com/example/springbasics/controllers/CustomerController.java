package com.example.springbasics.controllers;

import com.example.springbasics.model.Customer;
import com.example.springbasics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static int customerId = 0;
    private static List<Customer> customer = new ArrayList<>();

    @Autowired
    CustomerRepository repository;

    @GetMapping("/customers/get")
    public ResponseEntity <List<Customer>> getCustomers() {
        Customer mCustomer = new Customer();
        mCustomer.setId(Long.valueOf(customerId));
        mCustomer.setFirstName("FirstName "+ customerId);
        mCustomer.setLastName("LastName "+ customerId);
        customer.add(mCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers/create")
    public List<Customer> createCustomer (@RequestBody Customer customers) {
        Customer mCustomer = new Customer();
        mCustomer.setFirstName(customers.getFirstName());
        mCustomer.setLastName(customers.getLastName());
        mCustomer.setEmail(customers.getEmail());
        mCustomer.setPassword(customers.getPassword());
        repository.save(mCustomer);
        return customer;
    }

}
