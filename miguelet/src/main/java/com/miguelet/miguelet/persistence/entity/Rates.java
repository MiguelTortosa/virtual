package com.miguelet.miguelet.persistence.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.format.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "T_RATES")
@Table()
@Data
public class Rates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "brand_id")
    @ManyToOne
    private Brand brand;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "currency_code")
    @ManyToOne
    private Currency currency;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Integer price;
}
