package com.bksoftware.sellingweb.entities;


import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "buy_form")
public class BuyForm implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "phone_number")

    @NotNull
    private int phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String address;

    private String note;

    @NotNull
    private LocalDate date;

    @NotNull
    private boolean checked;

    @NotNull
    private boolean status;
}
