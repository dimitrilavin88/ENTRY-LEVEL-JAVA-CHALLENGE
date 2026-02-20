package com.challenge.api.store;

import com.challenge.api.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeStore {

    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public Optional<Employee> findByUuid(UUID uuid) {
        return Optional.ofNullable(employees.get(uuid));
    }

    public Employee save(Employee employee) {
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        employees.put(employee.getUuid(), employee);
        return employee;
    }
}
