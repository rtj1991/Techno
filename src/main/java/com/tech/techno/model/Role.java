package com.tech.techno.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;


    //    bi-directional many-to-many association to Module
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_module"
            , joinColumns = {
            @JoinColumn(name = "role")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "module")
            }
    )
    private List<Module> modules;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    public Role(String role) {
        this.role = role;
    }
    public Role(){

    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                '}';
    }
}
