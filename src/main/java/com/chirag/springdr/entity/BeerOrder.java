package com.chirag.springdr.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class BeerOrder {
    public BeerOrder(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerRef, Customer customer, Set<BeerOrderLine> beerOrderLines, BeerOrderShipment beerOrderShipment) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.customerRef = customerRef;
        setCustomer(customer);
        this.beerOrderLines = beerOrderLines;
        setBeerOrderShipment(beerOrderShipment);
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Long version;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder")
    private Set<BeerOrderLine> beerOrderLines;

    public boolean isNew(){
        return this.id == null;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customer.getBeerOrders().add(this);
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    private BeerOrderShipment beerOrderShipment;


    public void setBeerOrderShipment(BeerOrderShipment shipment) {
        this.beerOrderShipment = shipment;
        shipment.setBeerOrder(this);
    }

}
