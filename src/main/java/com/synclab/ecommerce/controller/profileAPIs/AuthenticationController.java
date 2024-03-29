package com.synclab.ecommerce.controller.profileAPIs;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.model.Cart;
import com.synclab.ecommerce.model.Role;
import com.synclab.ecommerce.model.User;
import com.synclab.ecommerce.model.DTOs.LoginCredentials;
import com.synclab.ecommerce.model.DTOs.LoginResponse;
import com.synclab.ecommerce.model.DTOs.SignupCredentials;
import com.synclab.ecommerce.security.utility.JWTUtils;
import com.synclab.ecommerce.security.utility.UserDetailsServiceImplementation;
import com.synclab.ecommerce.service.cart.CartServiceImplementation;
import com.synclab.ecommerce.service.role.RoleServiceImplementation;
import com.synclab.ecommerce.service.user.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder pe;
    
    @Autowired
    private AuthenticationManager am;
    
    @Autowired
    private UserDetailsServiceImplementation udsi;

    @Autowired
    private UserServiceImplementation usi;

    @Autowired
    private RoleServiceImplementation rsi;


    // allows the user to access get an authentication token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials req) throws Exception {

    	try {
        	am.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		} catch (Exception e) {
			System.out.println("invalid credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
    	
    	final UserDetails userDetails = udsi.loadUserByUsername(req.getUsername());
    	final String jwt = JWTUtils.generateToken(userDetails);
    	
    	return ResponseEntity.ok(new LoginResponse(jwt));
    	
    }

    // allows clients to register users 
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> signup(@RequestBody SignupCredentials request) {

        if (request == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        SignupCredentials credentials = request;
        
        //check username availability
        if (usi.findByAccount_username(credentials.getUsername()) != null)
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already in use");
        
        //check email availability
        if (usi.findByAccount_email(credentials.getEmail()) != null)
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already in use");
        
        //check password 
        String password = credentials.getPassword();
        if (password.length() < 8 || password.length() > 28 || password.contains(" "))
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Password should be 8-28 characters long, spaces are not allowed");
        
        //create a new user and account with specified credentials
   
        Account account = new Account();
        account.setEmail(credentials.getEmail());
        account.setUsername(credentials.getUsername());
        account.setPassword(pe.encode(credentials.getPassword()));
        account.setBirthDate(credentials.getBirth());
        Role role = rsi.findByName("ROLE_USER");
        if (role == null)
        	return new ResponseEntity<String>("ROLE_USER not found in DB", HttpStatus.NOT_FOUND);
        account.addRole(role);
        
        User user = new User();
        user.setFirstName(credentials.getName());
        user.setLastName(credentials.getLastName());
        user.setSignupDate(new Date());
        user.setAccount(account);

        // add entity to db
        user = usi.insert(user);

        return ResponseEntity.ok(user.getUserId() + " successfully registered");

    }
    
}
