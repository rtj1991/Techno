package com.tech.techno.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "grn_header")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "grnno")
public class GrnHeader implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer grnno;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "selling_price_total",nullable = false)
    private double sellingPriceTotal;

    @Column(name = "payment")
    private int payment;

    @Column(name = "total_discount",nullable = false)
    private double totalDiscount;

    @Column(name = "status")
    private int status;

    @Column(name = "deleted")
    private int deleted;

    /*
    many-to-one relation User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorized_by")
    @JsonManagedReference
    private User authorizedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    /*
    many-to-one relation user
     */
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdBy;

    /*
    many-to-one relation user
     */
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonManagedReference
    private User modifiedBy;

    /*
    one-to-many relation grndetail
     */
    @OneToMany(mappedBy = "grnno")
    @JsonBackReference
    private List<GrnDetail> grn;

    @Override
    public String toString(){
        return "GrnHeader{" +
                "grnno=" +
                grnno +
                '}';
    }


}
