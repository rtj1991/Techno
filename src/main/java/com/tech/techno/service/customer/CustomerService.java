package com.tech.techno.service.customer;


import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Map;

public interface CustomerService {
    Page<Customer> getAllCustomerByStatus(Pageable pageable);

    public Customer saveCustomer(Map<String,String>map, DefaltSave defaltSave,Customer customer);

    Customer getCustomerById(Integer id);

    Customer deleteCustomer(Integer id)throws Exception;

    Customer updateCustomer (Map<String,String>map);
}
