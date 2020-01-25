package com.jelurida.test.Controller;

import java.util.List;

import com.jelurida.test.model.EmployeeEntity;
import com.jelurida.test.services.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();

        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
            throws NotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);

        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity employee)
            throws NotFoundException {
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
            throws NotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }

}
