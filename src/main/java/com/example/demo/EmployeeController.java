package com.example.demo;

import com.example.demo.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
//@RequestMapping("/employees")


public class EmployeeController{

    @Value("${RENDER_URL}")
    private String renderURL;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/test")
    public String testService() {
        return employeeService != null ? "EmployeeService is injected!" : "EmployeeService is NOT injected.";
    }

    @Secured("ROLE_EMPLOYEE")
    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @GetMapping("/profile")
    public String getEmployeeProfile() {
        return "Employee Profile Data";
    }

    // Accessible by admins only
    @Secured("ROLE_ADMIN")
    @GetMapping("/manage")
    public String manageEmployees() {
        return "Manage Employees Section";
    }

    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){
        try{
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error fetching employees: " + e.getMessage());
        }

    }


    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @PostMapping("/employees/add")
    public ResponseEntity<String> hireEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        try{
            employeeService.hireEmployee(employeeDTO);
            return ResponseEntity.ok("Employee added successfully");
        }catch(Exception e){
            System.out.println("Error adding employee:" + e.getMessage());
            return ResponseEntity.status(500).body("Error adding employee: " + e.getMessage());
        }

    }
    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @PutMapping("/employees/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeId,
                                                 @RequestBody EmployeeDTO employeeDTO){
        System.out.println("employee dto " + employeeDTO);
        employeeService.updateEmployee(employeeId,employeeDTO);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @PutMapping("/employees/promote/{id}")
    public ResponseEntity<String> promoteEmployee(@PathVariable("id") Long employeeId,@RequestBody PromoteDTO promoteDTO){
        employeeService.promoteEmployee(employeeId,promoteDTO.getRoleId(), promoteDTO.getSalary());
        return ResponseEntity.ok("Employee promoted successfully");
    }

    @CrossOrigin(origins = {
            "${RENDER_URL}"
    })
    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<String> promoteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}