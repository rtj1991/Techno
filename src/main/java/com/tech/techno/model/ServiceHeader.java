package com.tech.techno.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_header")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ServiceHeader implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "service_charge")
    private double serviceCharge;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    //    many-to-one relation employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    @JsonManagedReference
    private Employee employee;

    //    one-to-one relation customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    @JsonManagedReference
    private Customer customer;

    //    one-to-many relation service detail
    @OneToMany(mappedBy = "serviceNo")
    @JsonBackReference
    private List<ServiceDetail> serviceNo;

    @Override
    public String toString() {
        return "ServiceHeader" +
                "id=" + id +
                '}';
    }
}
