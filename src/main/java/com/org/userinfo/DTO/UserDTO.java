package com.org.userinfo.DTO;


import com.org.userinfo.Configurations.Response;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserDTO extends Response {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String address;

    private String phone;

    private String email;

    private String password;

    private LocalDate dob;

    private String role;

    private boolean enable;

    private String token;

    private int age;

    public UserDTO(Long id, String firstName,
                   String lastName, String username,
                   String address, String phone,
                   String email, String password,
                   LocalDate dob, String role,
                   boolean enable, String token
    ) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
        this.enable = enable;
        this.token = token;
    }

    public UserDTO() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAge() {
        if (null != this.getDob()) {
            Long year = ChronoUnit.YEARS.between(this.getDob(), LocalDate.now());
            return year.intValue();
        } else return 0;
    }

    public void setAge(int age) {
        this.age = age;

    }
}
