package org.myproject.repositories;


import org.myproject.entities.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    CustomerDetails findCustomerDetailsById(long id);

    @Query("SELECT c FROM Customer c JOIN FETCH c.customerDetails")
    List<CustomerDetails> findAllCustomersWithDetails();
}
