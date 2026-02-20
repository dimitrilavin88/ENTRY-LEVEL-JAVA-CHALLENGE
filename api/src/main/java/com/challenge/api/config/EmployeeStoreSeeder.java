package com.challenge.api.config;

import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.store.EmployeeStore;
import java.time.Instant;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** Seeds the in-memory store with one sample employee at startup. */
@Component
public class EmployeeStoreSeeder implements ApplicationRunner {

    private final EmployeeStore employeeStore;

    public EmployeeStoreSeeder(EmployeeStore employeeStore) {
        this.employeeStore = employeeStore;
    }

    @Override
    public void run(ApplicationArguments args) {
        EmployeeImpl employee = new EmployeeImpl();
        employee.setUuid(null); // store will generate
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setFullName("Jane Doe");
        employee.setEmail("jane.doe@example.com");
        employee.setJobTitle("Software Engineer");
        employee.setSalary(85000);
        employee.setAge(30);
        employee.setContractHireDate(Instant.parse("2023-01-15T00:00:00Z"));
        employee.setContractTerminationDate(null);
        employeeStore.save(employee);
    }
}
