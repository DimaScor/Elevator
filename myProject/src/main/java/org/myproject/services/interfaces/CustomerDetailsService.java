package org.myproject.services.interfaces;

import org.myproject.entities.CustomerDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public interface CustomerDetailsService {
    CustomerDetails getCustomerDetailsById(Long id);
    List<CustomerDetails> getAllCustomerDetails();
    CustomerDetails createCustomerDetails(CustomerDetails details, Long customerId);
    CustomerDetails updateCustomerDetails(Long id, CustomerDetails customerDetails,Long customerId);
    void deleteCustomerDetails(Long id);
}

