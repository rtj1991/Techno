package com.tech.techno.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nic")
    private String nic;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "menu_collapse")
    private boolean menuCollapse;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_modify")
    private Date timestampModify;

    /*
    bi-directional many-to-one relation customer
     */
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Customer> createdCustomers;

    /*
    bi-directonal many-to-one customer
     */
    @OneToMany(mappedBy = "lastModifiedBy")
    @JsonBackReference
    private List<Customer> modifiedCustomer;

    /*
    bi directional many-to-one item
     */
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Item> createdUser;

    /*
    bi directional many-to-one item
    */
    @OneToMany(mappedBy = "lastModifiedBy")
    @JsonBackReference
    private List<Item> ModifiedUser;

    //    bi-directional many-to-many association to Role
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role"
            , joinColumns = {
            @JoinColumn(name = "user")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "role")
    }
    )
    private List<Role> roles;

    //    bi directional many-to-one suplier
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Supplier> suplierCreatedBy;

    //    bi directional many-to-one suplier
    @OneToMany(mappedBy = "lastModifiedBy")
    @JsonBackReference
    private List<Supplier> suplierModifiedBy;

    //    bi directional many-to-one catogory
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Category> catCreatedBy;

    //    bi directional many-to-one catogory
    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<Category> catModifiedBy;

    //    bi directional many-to-one priceList
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<PriceList> priceCreatedBy;

    //    bi directional many-to-one priceList
    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<PriceList> priceModifiedBy;

    //    bi directional many-to-one stock
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Stock> stockCreatedBy;

    //    bi directional many-to-one stock
    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<Stock> stockModifiedBy;

    /*
    one-to-many relation grnheader authorizedBy
     */
    @OneToMany(mappedBy = "authorizedBy")
    @JsonBackReference
    private List<GrnHeader> GrnauthorizedBy;

    /*
    one-to-many relation grnheader created by
     */
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<GrnHeader> grnCreatedBy;

    //    one-to-many relation grnheader created by
    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<GrnHeader> grnModifiedBy;

    //    one-to-many relation grnheader created by
    @OneToMany(mappedBy = "createdby")
    @JsonBackReference
    private List<GrnDetail> grnDeCreatedby;

    //    one-to-many relation grnheader created by
    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<GrnDetail> grnDeModifiedBy;

//    one-to-many relation InvoiceHeader created by
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<InvoiceHeader> invoiceCreatedBy;

    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<InvoiceHeader> invoiceModifiedBy;

//    one-to-many relation receipt header
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<ReceiptHeader> receiptCreatedBy;

    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<ReceiptHeader> receiptModifiedBy;

//    one-to-many relation employee
    @OneToMany(mappedBy = "createdBy")
    @JsonBackReference
    private List<Employee> empCreatedBy;

    @OneToMany(mappedBy = "modifiedBy")
    @JsonBackReference
    private List<Employee> empModifiedBy;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

}
