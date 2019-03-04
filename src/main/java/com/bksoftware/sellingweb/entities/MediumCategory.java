package com.bksoftware.sellingweb.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "medium_category")
@SecondaryTables({
        @SecondaryTable(name = "big_category")
})
public class MediumCategory  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "big_category_id",nullable = false)
    @NotNull
    private BigCategory bigCategory;

    private boolean status;
}
