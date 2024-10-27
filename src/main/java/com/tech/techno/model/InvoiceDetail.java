package com.tech.techno.model;

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

@Entity
@Table(name = "invoice_detail")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class InvoiceDetail implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qty")
    private double qty;

    @Column(name = "price_selling_with_tax")
    private double priceSellingWithTax;

    @Column(name = "price_selling_tax")
    private double priceSellingTax;

    @Column(name = "price_selling_without_tax")
    private double priceSellingWithoutTax;

    @Column(name = "return_qty")
    private double returnQty;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    /*
    many-to-one relation invoice header
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice")
    @JsonManagedReference
    private InvoiceHeader invoiceId;

    @Override
    public String toString(){
        return "InvoiceDetail{" +
                "id=" + id +
                '}';
    }
}
