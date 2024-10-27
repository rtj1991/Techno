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
@Table(name = "grn_detail")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class GrnDetail implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qty")
    private double qty;

    @Column(name = "unit_discount")
    private double unitDiscount;

    @Column(name = "item_discount")
    private double itemDiscount;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_ceated")
    private Date timestampCeated;

    /*
    many-to-one relation item
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item")
    @JsonManagedReference
    private Item item;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modified")
    private Date timestampModified;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonManagedReference
    private User createdby;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonManagedReference
    private User modifiedBy;

    /*
    many-to-one relation grnheader
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grn")
    @JsonManagedReference
    private GrnHeader grnno;

    @Override
    public String toString(){
        return "GrnDetail{" +
                "id=" + id +
                '}';
    }
}
