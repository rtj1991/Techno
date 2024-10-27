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
@Table(name = "invoice_header")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class InvoiceHeader implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "amount_due")
    private double amountDue;

    @Column(name = "amount_paid")
    private double amountPaid;

    @Column(name = "note")
    private String note;

    @Column(name = "total_discount")
    private double totalDiscount;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "status")
    private int status;

    @Column(name = "paid_status")
    private int paidStatus;

    @Column(name = "print_status")
    private int printStatus;

    @Column(name = "total_gross")
    private double totalGross;

    @Column(name = "total_net")
    private double totalNet;

    @Column(name = "total_tax")
    private double totalTax;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    //    many-to-one relation to user
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdBy;

    //    many-to-one relation to user
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonManagedReference
    private User modifiedBy;

    @OneToMany(mappedBy = "invoiceId")
    @JsonBackReference
    private List<InvoiceDetail>invoiceId;

//    many-to-one relation customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customerId;

//    one-to-many relation receipt
    @OneToMany(mappedBy = "invoiceNo")
    @JsonBackReference
    private List<ReceiptHeader> invoiceNo;

    @Override
    public String toString(){
        return "InvoiceHeader{" +
                "id=" + id +
                '}';
    }
}
