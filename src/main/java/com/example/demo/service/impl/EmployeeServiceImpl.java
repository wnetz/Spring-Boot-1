package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee)
    {
        return  employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id)
    {
        return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee e = getEmployeeById(id);
        e.setEmail(employee.getEmail());
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        employeeRepository.save(e);
        return e;
    }

    @Override
    public void deleteEmployee(long id)
    {
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }

}
