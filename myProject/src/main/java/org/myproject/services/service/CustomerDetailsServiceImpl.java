package org.myproject.services.service;

import org.myproject.entities.CustomerDetails;
import org.myproject.entities.User;
import org.myproject.repositories.CustomerDetailsRepository;
import org.myproject.repositories.UserRepository;
import org.myproject.services.interfaces.CustomerDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private CustomerDetailsRepository customerDetailsRepository;
    private UserRepository userRepository;

    @Override
    public CustomerDetails createCustomerDetails(CustomerDetails details, Long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + customerId));
        details.setUser(customer);
        return details;
    }

    @Override
    public CustomerDetails updateCustomerDetails(Long id, CustomerDetails details, Long customerId) {
        CustomerDetails existingDetails = customerDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid details ID: " + id));
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + customerId));
        existingDetails.setUser(customer);
        existingDetails.setFirstName(details.getFirstName());
        existingDetails.setAddress(details.getAddress());
        existingDetails.setPhoneNumber(details.getPhoneNumber());
        return customerDetailsRepository.save(existingDetails);
    }

    @Override
    public void deleteCustomerDetails(Long id) {
        customerDetailsRepository.deleteById(id);
    }

    @Override
    public List<CustomerDetails> getAllCustomerDetails() {
        return customerDetailsRepository.findAll();
    }

    @Override
    public CustomerDetails getCustomerDetailsById(Long id) {
        return customerDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid details ID: " + id));
    }

}
