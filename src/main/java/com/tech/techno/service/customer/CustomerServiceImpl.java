package com.tech.techno.service.customer;

import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Customer;
import com.tech.techno.repository.CustomerRepository;
import com.tech.techno.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomerByStatus(Pageable pageable) {
        return customerRepository.findAllByStatus(Const.STATUS_ACTIVE, pageable);
    }

    @Override
    public Customer saveCustomer(Map<String, String> map, DefaltSave defaltSave, Customer customer) {

        int gender = Integer.parseInt(map.get("gender"));

        customer.setFirstName(map.get("myName"));
        customer.setLastName(map.get("lastname"));
        customer.setAddress(map.get("addres"));
        customer.setMobileNo(map.get("phone"));
        customer.setRemark(map.get("remark"));
        customer.setKeywords(map.get("myName") + " " + map.get("lastname") + " " + map.get("addres") + " " + map.get("phone"));
        customer.setStatus(1);
        customer.setGender(gender);

        customerRepository.save(customer);

        return customer;
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer deleteCustomer(Integer id) throws Exception {
        Customer customer = customerRepository.findById(id);

        customer.setStatus(Const.STATUS_DEACTIVE);
        customerRepository.save(customer);

        return customer;
    }

    @Override
    public Customer updateCustomer(Map<String, String> map) {

        String cusId = map.get("customerId");
        int id = Integer.parseInt(cusId);

        Customer customId = customerRepository.findById(id);

        int gender = Integer.parseInt(map.get("gender"));

        customId.setGender(gender);
        customId.setFirstName(map.get("firstName"));
        customId.setLastName(map.get("lastName"));
        customId.setAddress(map.get("address"));
        customId.setMobileNo(map.get("mobileNo"));
        customId.setRemark(map.get("remark"));
        customId.setStatus(1);

        customerRepository.save(customId);

        return customId;
    }
}
