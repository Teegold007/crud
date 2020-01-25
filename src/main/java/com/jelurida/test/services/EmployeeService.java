package com.jelurida.test.services;

import com.jelurida.test.model.EmployeeEntity;
import com.jelurida.test.repository.EmployeeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws NotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new NotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws NotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());

        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteEmployeeById(Long id) throws NotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No employee record exist for given id");
        }
    }
}