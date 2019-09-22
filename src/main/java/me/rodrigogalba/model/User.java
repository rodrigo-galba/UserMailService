package me.rodrigogalba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.rodrigogalba.model.listener.UserEntityListener;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@EntityListeners({AuditingEntityListener.class, UserEntityListener.class})
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
@Data
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
    private boolean admin;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = false, updatable = false)
    protected Date createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date updatedDate;

    @JsonIgnore
    public Boolean isAdmin() {
        return admin;
    }

    @JsonIgnore
    public String getEncryptedPassword() {
        return password;
    }

    public void merge(User userDetails) {
        this.name = userDetails.name;
        this.login = userDetails.login;
        this.email = userDetails.email;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
