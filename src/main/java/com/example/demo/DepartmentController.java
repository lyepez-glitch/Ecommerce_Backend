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
public class DepartmentController{


    @Autowired
    private DepartmentService departmentService;

    @Value("${RENDER_URL}")
    private String renderURL;
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

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments(){
        try{
            return ResponseEntity.ok(departmentService.getAllDepartments());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error fetching departments: " + e.getMessage());
        }

    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PostMapping("/departments/add")
    public ResponseEntity<String> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        try{
            departmentService.addDepartment(departmentDTO);
            return ResponseEntity.ok("Department added successfully");
        }catch(Exception e){
            System.out.println("Error adding department:" + e.getMessage());
            return ResponseEntity.status(500).body("Error adding department: " + e.getMessage());
        }

    }
    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PutMapping("/departments/update/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") Long departmentId,
                                                 @RequestBody DepartmentDTO departmentDTO){
        departmentService.updateDepartment(departmentId,departmentDTO);
        return ResponseEntity.ok("Department updated successfully");
    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @DeleteMapping("/departments/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully");
    }
}