package com.synclab.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    // fields

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_suspended")
    private Boolean isSuspended = false;

    @Column(name = "is_banned")
    private Boolean isBanned = false;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accounts_roles",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "account_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id",
                            nullable = false, updatable = false)})
    
    private List<Role> role = new ArrayList<>();
    
    // constructors
    
    public Account() {}
    
    public Account(String username, String email, String password, String phone, Date birtDate, List<Role> role) {
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.phone = phone;
    	this.birthDate=birtDate;
    	this.role = role;
    }
    

    
}
