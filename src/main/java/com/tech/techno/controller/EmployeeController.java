package com.tech.techno.controller;


import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Customer;
import com.tech.techno.model.Employee;
import com.tech.techno.service.employee.EmployeeService;
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

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public String empList(Model model, Pageable pageable) throws Exception {
        PageRequest request = new PageRequest(pageable.getPageNumber(), 10, Sort.Direction.DESC, "id");
        Page<Employee> employee = employeeService.getAllActiveEmployee(request);
        PageWrapper<Employee> page = new PageWrapper<>(employee, "/employee/list");

        model.addAttribute("employee", employee);
        model.addAttribute("page", page);
        return "system/employee/emp_list";
    }

    @RequestMapping("/create")
    public String createEnployee(Model model) {
        DefaltSave defaltSave = new DefaltSave();
        model.addAttribute("defaltSave", defaltSave);
        return "system/employee/create_emp";
    }

    @PostMapping("/saveEmp")
    public String saveEmployee(@ModelAttribute Employee employee, @RequestParam Map<String, String> map, DefaltSave defaltSave) {

        employeeService.saveEmployee(employee, map, defaltSave);

        return "redirect:/employee/" + employee.getId();
    }

    @RequestMapping("/{id}")
    public String editEmp(@PathVariable Integer id, Model model) {
        if (id != null) {
            Employee employee = new Employee();
            employee.setId(id);
            Employee empById = employeeService.getEmpById(id);

            model.addAttribute("employee", empById);
            model.addAttribute("empid", id);

        }
        return "/system/employee/emp_edit";
    }

    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable Integer id) {
        if (id != null) {
            employeeService.deleteEmp(id);
        }
        return "redirect:/employee/list/";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(@RequestParam Map<String, String> map) throws ParseException {
        if (map != null && !map.isEmpty()) {
            employeeService.updateEmp(map);
        }
        return "redirect:/employee/list";
    }
}
