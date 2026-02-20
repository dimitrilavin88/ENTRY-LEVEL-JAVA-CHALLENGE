package com.challenge.api.service;

import com.challenge.api.model.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.store.EmployeeStore;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final EmployeeStore employeeStore;

    public EmployeeService(EmployeeStore employeeStore) {
        this.employeeStore = employeeStore;
    }

    public List<Employee> getAllEmployees() {
        return employeeStore.findAll();
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return employeeStore
                .findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        Employee employee = new EmployeeImpl();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        String first = request.getFirstName() != null ? request.getFirstName() : "";
        String last = request.getLastName() != null ? request.getLastName() : "";
        employee.setFullName((first + " " + last).trim());
        employee.setEmail(request.getEmail());
        employee.setJobTitle(request.getJobTitle());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setUuid(null);
        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);
        return employeeStore.save(employee);
    }
}
