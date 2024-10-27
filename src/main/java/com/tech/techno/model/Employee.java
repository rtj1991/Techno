package com.tech.techno.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    @Length(min = 3)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    @Length(min = 3)
    private String lastName;

    @Column(name = "surname")
    @NotEmpty
    @Length(min = 3)
    private String surname;

    @Column(name = "status")
    private int status;

    @Column(name = "nic_no")
    @NotEmpty
    private String nicNo;

    @Column(name = "emp_no", unique = true)
    private String empNo;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "remark")
    private String remark;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    //    many-to-one relation user
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",referencedColumnName = "id")
    @JsonManagedReference
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by",referencedColumnName = "id")
    @JsonManagedReference
    private User modifiedBy;

    //    one-to-many relation service header
    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<ServiceHeader> employee;

    //    One-To-Many relation EmployeeJobCard
    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<EmployeeJobCard> createdEmployee;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", status=" + status +
                ", nicNo='" + nicNo + '\'' +
                ", empNo='" + empNo + '\'' +
                ", gender=" + gender +
                ", remark='" + remark + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", timestampCreated=" + timestampCreated +
                ", timestampModified=" + timestampModified +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                ", employee=" + employee +
                ", createdEmployee=" + createdEmployee +
                '}';
    }

    public Employee(){

    }

}
