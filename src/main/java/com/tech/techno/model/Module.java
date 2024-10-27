package com.tech.techno.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Data
public class Module implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "module",nullable = false,unique = true)
    private String module;

    @Column(name="fa_icon")
    private String faIcon;

    private String route;

    @Column(name = "status_active")
    private Integer statusActive;

    @Column(name="status_redirect")
    private Integer statusRedirect;

    @Column(name="status_visibility")
    private Integer statusVisibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    @JsonManagedReference
    private Module parent;

    @OneToMany(mappedBy = "parent")
    @JsonBackReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Module> modules;

    @ManyToMany(mappedBy = "modules")
    @JsonIgnore
    private List<Role> roles;

    @Override
    public String toString(){
        return "Module{"+
                "id=" + id +
                '}';
    }
}
