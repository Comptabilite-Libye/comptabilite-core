/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.domaine;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Table(name = "users", schema = "access")
@Entity
@Audited
@AuditTable("users_AUD")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false, name = "user_name", columnDefinition = "varchar(200)")
    private String userName;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, name = "pass_cry")
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Lob
    @Column(name = "signature",columnDefinition = ("varbinary(MAX)"))
    private byte[] signature;

    @Column(nullable = false, name = "password")
    private String passwordDecry;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

//    @Override
//    public String toString() {
//        return "User{" + "id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
//    }
    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", signature=" + signature + ", passwordDecry=" + passwordDecry + '}';
    }

  

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getPasswordDecry() {
        return passwordDecry;
    }

    public void setPasswordDecry(String passwordDecry) {
        this.passwordDecry = passwordDecry;
    }
    
    

}
