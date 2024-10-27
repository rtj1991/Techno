package com.tech.techno.service.employee;


import com.tech.techno.dto.AbstractSearchDetail;
import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Page<Employee> getAllActiveEmployee(Pageable pageable)throws Exception;

    public Employee saveEmployee(Employee employee, Map<String,String>map, DefaltSave defaltSave);

    Employee getEmpById(Integer id);

    Employee deleteEmp(Integer id);

    Employee updateEmp(Map<String,String>map) throws ParseException;
}
