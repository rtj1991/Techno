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
@Table(name = "price_list")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PriceList implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "old_price")
    private Double oldPrice;

    @Column(name = "rate")
    private double rate;

    @Column(name = "price_status")
    private int priceStatus;

    @Column(name = "status")
    private int status;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonManagedReference
    private User modifiedBy;

    @OneToMany(mappedBy = "priceId")
    @JsonBackReference
    private List<Stock> priceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item",nullable = false)
    @JsonIgnore
    private Item item;

    @Override
    public String toString(){
        return "PriceList{" +
                "id=" + id +
                '}';
    }
}
