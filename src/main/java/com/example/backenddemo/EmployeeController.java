package com.example.backenddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EmployeeController {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/save", consumes = "application/json")
    public ResponseEntity saveEmployee(@RequestBody Employee employee){
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(employee.name);
        entity.setDepartment(employee.department);
        repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("EmployeeEntity created");
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeEntity>> employees(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody EmployeeEntity employeeEntity) {
        repository.save(employeeEntity);
        return ResponseEntity.accepted().body("EmployeeEntity edited");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@RequestParam Integer id){
        repository.deleteById(id);
        return ResponseEntity.accepted().body("EmployeeEntity Deleted");
    }
}
