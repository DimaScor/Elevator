package org.myproject.controllers;

import org.myproject.entities.CustomerDetails;
import org.myproject.services.service.CustomerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-details")
public class CustomerDetailsController {
    @Autowired
    private final CustomerDetailsServiceImpl customerDetailsServiceImpl;

    public CustomerDetailsController(CustomerDetailsServiceImpl customerDetailsServiceImpl) {
        this.customerDetailsServiceImpl = customerDetailsServiceImpl;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CustomerDetails> createCustomerDetails(@RequestBody CustomerDetails details, @PathVariable Long customerId) {
        CustomerDetails createdDetails = customerDetailsServiceImpl.createCustomerDetails(details, customerId);
        return ResponseEntity.ok(createdDetails);
    }

    @PutMapping("/{id}/{customerId}")
    public ResponseEntity<CustomerDetails> updateCustomerDetails(@RequestBody CustomerDetails details, @PathVariable Long id, @PathVariable Long customerId) {
        CustomerDetails updatedDetails = customerDetailsServiceImpl.updateCustomerDetails(id, details, customerId);
        return ResponseEntity.ok(updatedDetails);
    }
}
