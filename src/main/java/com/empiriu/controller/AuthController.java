package com.empiriu.controller;

import com.empiriu.config.JwtTokenProvider;
import com.empiriu.exception.AppException;
import com.empiriu.model.Employee;
import com.empiriu.model.Role;
import com.empiriu.model.RoleName;
import com.empiriu.payload.ResponseMessage;
import com.empiriu.payload.JwtResponse;
import com.empiriu.payload.LoginForm;
import com.empiriu.payload.SignUpForm;
import com.empiriu.repository.EmployeeRepository;
import com.empiriu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;

    private EmployeeRepository employeeRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    //    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, EmployeeRepository employeeRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.employeeRepository = employeeRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.tokenProvider = tokenProvider;
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication, loginForm.isRememberMe());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm) {
//        if(employeeRepository.existsByUsername(signUpForm.getUsername())) {
//            return new ResponseEntity(new ResponseMessage("Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        if(employeeRepository.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity(new ResponseMessage("Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating employee's account
        Employee employee = new Employee(signUpForm.getName(),
                "https://image.freepik.com/free-photo/elegant-man-with-thumbs-up_1149-1595.jpg",
                signUpForm.getEmail(),
                passwordEncoder.encode(signUpForm.getPassword()),
                20,
                "m",
                signUpForm.getPosition(),
                signUpForm.getPhone());

        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
