package com.tech.techno.service.employee;

import com.tech.techno.dto.AbstractSearchDetail;
import com.tech.techno.dto.DefaltSave;
import com.tech.techno.model.Employee;
import com.tech.techno.repository.EmployeeRepository;
import com.tech.techno.util.Const;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> getAllActiveEmployee(Pageable pageable) {
        return employeeRepository.findAllByStatus(Const.STATUS_ACTIVE, pageable);
    }

    @Override
    public Employee saveEmployee(Employee employee, Map<String, String> map, DefaltSave defaltSave) {

        int gender = Integer.parseInt(map.get("gender"));
        employee.setStatus(1);
//        employee.setFirstName(map.get("myName"));
        employee.setDateOfBirth(defaltSave.defaltDate);
//        employee.setLastName(map.get("lastname"));
//        employee.setNicNo(map.get("nicno"));
//        employee.setSurname(map.get("surename"));
//        employee.setRemark(map.get("remark"));
        employee.setGender(gender);
        employeeRepository.save(employee);

        Integer id = employee.getId();
        Employee byId = employeeRepository.findById(id);
        byId.setEmpNo("Emp" + id);
        employeeRepository.save(byId);

        return employee;
    }

    @Override
    public Employee getEmpById(Integer id) {

        return employeeRepository.findById(id);
    }

    @Override
    public Employee deleteEmp(Integer id) {
        Employee employee = employeeRepository.findById(id);
        employee.setStatus(Const.STATUS_DEACTIVE);
        return employee;
    }

    @Override
    public Employee updateEmp(Map<String, String> map) throws ParseException {

        int id = Integer.parseInt(map.get("empid"));
        int gender = Integer.parseInt(map.get("gender"));
        String d = map.get("dateOfBirth");

        Date da = new SimpleDateFormat("yyyy-MM-dd").parse(d);

        Employee empById = employeeRepository.findById(id);
        empById.setGender(gender);
        empById.setRemark(map.get("remark"));
        empById.setFirstName(map.get("firstName"));
        empById.setLastName(map.get("lastName"));
        empById.setSurname(map.get("surname"));
        empById.setNicNo(map.get("nicNo"));
        empById.setDateOfBirth(da);

        employeeRepository.save(empById);

        return empById;
    }
}
