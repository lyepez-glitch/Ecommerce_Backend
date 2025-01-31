package com.example.demo;

import com.example.demo.RoleService;
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
//@RequestMapping("/roles")
//
public class RoleController{

    @Value("${RENDER_URL}")
    private String renderURL;

    @Autowired
    private RoleService roleService;
//    @GetMapping("/test")
//    public String testService() {
//        return roleService != null ? "roleService is injected!" : "roleService is NOT injected.";
//    }

//    @Secured("ROLE_role")
//    @GetMapping("/profile")
//    public String getroleProfile() {
//        return "role Profile Data";
//    }
//
//    // Accessible by admins only
//    @Secured("ROLE_ADMIN")
//    @GetMapping("/manage")
//    public String manageroles() {
//        return "Manage roles Section";
//    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(){
        try{
            System.out.println("Roles fetched from service: " + roleService.getAllRoles());
            return ResponseEntity.ok(roleService.getAllRoles());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error fetching roles: " + e.getMessage());
        }

    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PostMapping("/roles/add")
    public ResponseEntity<String> addRole(@Valid @RequestBody RoleDTO roleDTO){
        try{
            roleService.addRole(roleDTO);
            return ResponseEntity.ok("role added successfully");
        }catch(Exception e){
            System.out.println("Error adding role:" + e.getMessage());
            return ResponseEntity.status(500).body("Error adding role: " + e.getMessage());
        }

    }
    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PutMapping("/roles/update/{id}")
    public ResponseEntity<String> updateRole(@PathVariable("id") Long roleId,
                                                 @RequestBody RoleDTO roleDTO){
        System.out.println("roleDTO in controller "+ roleDTO);
        roleService.updateRole(roleId,roleDTO);
        return ResponseEntity.ok("role updated successfully");
    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @DeleteMapping("/roles/delete/{id}")
    public ResponseEntity<String>deleteRole(@PathVariable("id") Long roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("role deleted successfully");
    }
}