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
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "itemcode")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "itemcode")
    private Integer itemcode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "gauge")
    private String gauge;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "item_area")
    private Double areaOfItem;

    @Column(name = "thickness")
    private String thickness;

    @Column(name = "item_refcode")
    private String itemRefcode;

    @Column(name = "status")
    private Integer status;

    @Column(name = "price_id")
    private Integer priceId;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    /*
    many-to-one relation to user
     */

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdBy;

    /*
    many-to-one relation
     */
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastmodified_By")
    @JsonManagedReference
    private User lastModifiedBy;

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<PriceList> item;

    /*
    one-to -one relation price-List
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "item")
    @JsonManagedReference
    private List<PriceList> productPriceList;

    /*
    many-to-one relation category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    @JsonManagedReference
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory")
    @JsonManagedReference
    private SubCategory subcategory;

    /*
    many-to-one relation supplier
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier")
    @JsonManagedReference
    private Supplier supplier;


    /*
    one-to-many relation grndetail
     */
    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<GrnDetail> grnItem;

    @Override
    public String toString() {
        return "item{" +
                "itemcode=" + itemcode +
                '}';
    }


}
