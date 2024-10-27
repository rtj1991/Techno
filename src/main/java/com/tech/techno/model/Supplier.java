package com.tech.techno.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "supplier")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(exclude = {"createdBy","lastModifiedBy"})
public class Supplier implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "company")
    private String company;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @Column(name = "credit_period")
    private Integer creditPeriod;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "status")
    private Integer status;

    @Column(name = "suplier_name")
    private String suplier_name;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestampCreated")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;


//    bi-directional many-to-one association to User
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnore
    private User createdBy;

//    bi-directional many-to-one association to User
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modifiedby")
    @JsonIgnore
    private User lastModifiedBy;

    /*
    one-to-many relation item
     */
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private List<Item> supplier;

    @Override
    public String toString(){
        return "Supplier{" +
                "id=" + id +
                '}';
    }
}
