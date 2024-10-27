package com.tech.techno.controller;

import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Customer;
import com.tech.techno.service.customer.CustomerService;
import com.tech.techno.util.Const;
import com.tech.techno.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String customerList(Model model, Pageable pageable) {

        PageRequest request = new PageRequest(pageable.getPageNumber(), 10, Sort.Direction.DESC, "id");
        Page<Customer> customer = customerService.getAllCustomerByStatus(request);
        PageWrapper<Customer> page = new PageWrapper<>(customer, "/customer/list");

        model.addAttribute("customer", customer);
        model.addAttribute("page", page);
        return "system/customer/customer_list";
    }

    @RequestMapping("/create")
    public String createCustomer(Model model) {
        DefaltSave defaltSave = new DefaltSave();
        model.addAttribute("defaltSave", defaltSave);

        return "system/customer/create_customer";
    }

    @PostMapping("/saveCustomer")
    public String savecustomer(@RequestParam Map<String, String> map, DefaltSave defaltSave, @ModelAttribute Customer customer) {

        customerService.saveCustomer(map, defaltSave, customer);

        return "redirect:/customer/" + customer.getId();
    }

    @RequestMapping("/{id}")
    private String editCustomer(@PathVariable Integer id, Model model) {
        if (id != null) {

            Customer customer = new Customer();
            customer.setId(id);
            Customer customers = customerService.getCustomerById(id);
            model.addAttribute("customer", customers);
            model.addAttribute("customer_id", id);

        }
        return "system/customer/customer_edit";
    }

    @RequestMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id) throws Exception {
        if (id != null) {
            customerService.deleteCustomer(id);

        }
        return "redirect:/customer/list";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestParam Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            customerService.updateCustomer(map);
        }
        return "redirect:/customer/list";
    }
}
