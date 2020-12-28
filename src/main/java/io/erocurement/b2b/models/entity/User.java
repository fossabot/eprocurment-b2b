package io.erocurement.b2b.models.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.*;


@Entity
@Table(name = "appuser")
@Data
@EqualsAndHashCode(callSuper = false)

@NoArgsConstructor
public class User extends AuditModel  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)

    private String username;

    @Column(length = 60)
    @NotBlank(message = "Password is mandatory!")

    private String password;

    private Boolean enabled;
    @NotBlank(message = "Firts Name is mandatory!")
    private String firtsName;
    @NotBlank(message = "Last Name is mandatory!")
    private String lastName;
    @Column(unique = false)
    @Email(message = "Check The format for this email")
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_rols", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles;


    public void setRoles(List<Role> collect) {
        collect.forEach(role -> {
            this.roles.add(role);
        });

    }

    public Collection<Role> getRoles() {
        return  this.roles;
    }

    public void addRole(Role role) {
        if (this.roles==null)
        {
            this.roles=new ArrayList<>();
        }
        this.roles.add(role);
    }
}
