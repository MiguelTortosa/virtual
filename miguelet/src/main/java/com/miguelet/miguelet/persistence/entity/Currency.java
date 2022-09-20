package com.miguelet.miguelet.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Entity()
@Table()
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code", nullable = false)
    private String code;
    private String symbol;
    private Integer decimals;
}
