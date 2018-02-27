package me.rodrigogalba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    @Length(min = 3)
    private String login;

    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Boolean isAdmin = false;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = false, updatable = false)
    protected Date createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date updatedDate;

    public User() {}

    @JsonIgnore
    public Boolean isAdmin() {
        return isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    @JsonIgnore
    public String getEncryptedPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void merge(User userDetails) {
        this.name = userDetails.name;
        this.login = userDetails.login;
        this.email = userDetails.email;
    }
}
