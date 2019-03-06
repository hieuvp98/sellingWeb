package com.bksoftware.sellingweb.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "product_details")
@SecondaryTable(name = "product")
public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_status")
    private boolean productStatus;

    @Column(name = "sold_date")
    private LocalDate soldDate;

    private int guarantee;

    private String present;

    private boolean status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",nullable = false)
    @JsonIgnore
    @NotNull
    private Product product;

}