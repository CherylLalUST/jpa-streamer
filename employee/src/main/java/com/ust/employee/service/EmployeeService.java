package com.ust.employee.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.employee.model.Employee;
import com.ust.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    public Map<String, List<Employee>> groupEmployeeByCity(){
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> findEmployee() {
        return employeeRepository.findAll() ;
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public Map<String, Long> groupEmployeeEducationUnderPaymentTier(int paymentTier){
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getPaymentTier() <= paymentTier)
                .collect(Collectors.groupingBy(Employee::getEducation,Collectors.counting()));
    }

    public Map<String, Long> countEmployeeByGender(){
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
    }
}
