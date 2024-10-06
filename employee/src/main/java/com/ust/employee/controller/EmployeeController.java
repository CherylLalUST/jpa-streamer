package com.ust.employee.controller;

import com.ust.employee.model.Employee;
import com.ust.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ust/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupEmployeeByCity(){
        return employeeService.groupEmployeeByCity();
    }

    @GetMapping("/findall")
    public List<Employee> findEmployee(){
        return employeeService.findEmployee();
    }
    @GetMapping("/groupByEducation/paymentTier/{tier}")
    public Map<String, Long> groupEmployeeEducationUnderPaymentTier(@PathVariable("tier") int paymentTier) {
        return employeeService.groupEmployeeEducationUnderPaymentTier(paymentTier);
    }
    @GetMapping("/countByGender")
    public Map<String, Long> countEmployeeByGender() {
        return employeeService.countEmployeeByGender();
    }
}
