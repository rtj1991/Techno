package com.tech.techno.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
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
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "remark")
    private String remark;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private int status;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modify")
    private Date timestampLastModify;

    /*
    many-to-one relation to user
     */
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdBy;

    /*
    many-to-one relation to user
     */
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonManagedReference
    private User lastModifiedBy;

    //    One-To-Many relation receipt
    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<ReceiptHeader> customer;

    //    one-to-many relation invoice header
    @OneToMany(mappedBy = "customerId")
    @JsonBackReference
    private List<InvoiceHeader> customerId;

    //    one-to-many relation service
    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    List<ServiceHeader> serviceCustomer;


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }
}
