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
//
//
public class EmployeeAuditController{


    @Autowired
    private EmployeeAuditService employeeAuditService;
//    @GetMapping("/test")
//    public String testService() {
//        return employeeService != null ? "EmployeeService is injected!" : "EmployeeService is NOT injected.";
//    }
//
//    @Secured("ROLE_EMPLOYEE")
//    @GetMapping("/profile")
//    public String getEmployeeProfile() {
//        return "Employee Profile Data";
//    }
//
//    // Accessible by admins only
//    @Secured("ROLE_ADMIN")
//    @GetMapping("/manage")
//    public String manageEmployees() {
//        return "Manage Employees Section";
//    }

    @Value("${RENDER_URL}")
    private String renderURL;

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @GetMapping("/employeeAudits")
    public ResponseEntity<?> getEmployeeAudits(){
        try{
            return ResponseEntity.ok(employeeAuditService.getAllEmployeeAudits());
        }catch(Exception e){

            return ResponseEntity.status(500).body("Error fetching audits: " + e.getMessage());
        }

    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PostMapping("/employeeAudits/add")
    public ResponseEntity<String> hireEmployee(@Valid @RequestBody EmployeeAuditDTO employeeAuditDTO){
        try{
            employeeAuditService.addAudit(employeeAuditDTO);
            return ResponseEntity.ok("Employee Audit added successfully");
        }catch(Exception e){
            System.out.println("Error adding employee Audit:" + e.getMessage());
            return ResponseEntity.status(500).body("Error adding employee Audit: " + e.getMessage());
        }

    }
    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PutMapping("/employeeAudit/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeAuditId,
                                                 @RequestBody EmployeeAuditDTO employeeAuditDTO){
        employeeAuditService.updateAudit(employeeAuditId,employeeAuditDTO);
        return ResponseEntity.ok("Employee Audit updated successfully");
    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @DeleteMapping("/employeeAudit/delete/{id}")
    public ResponseEntity<String> promoteEmployee(@PathVariable("id") Long employeeAuditId){
        employeeAuditService.deleteAudit(employeeAuditId);
        return ResponseEntity.ok("Employee Audit deleted successfully");
    }
}