package com.empiriu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Employee extends BaseEntity {
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String imageUrl;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotNull
    private int daysOff;

    @NotNull
    @Size(max = 25)
    private String position;

    @NotNull
    @Size(max = 14)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employees_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Holiday> holiday;

    public Employee() {

    }

    public Employee(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 300) String imageUrl, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 8, max = 100) String password, @NotNull @Size(min = 1, max = 2) int daysOff, @NotNull @Size(max = 1) String sex, @NotNull @Size(max = 25) String position, @NotNull @Size(max = 14) String phone) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.email = email;
        this.password = password;
        this.daysOff = daysOff;
        this.position = position;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getDaysOff() {
        return daysOff;
    }

    public void setId(int daysOff) {
        this.daysOff = daysOff;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDaysOff(int daysOff) {
        this.daysOff = daysOff;
    }

    public Set<Holiday> getHoliday() {
        return holiday;
    }

    public void setHoliday(Set<Holiday> holiday) {
        this.holiday = holiday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}