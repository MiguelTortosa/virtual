package com.miguelet.miguelet.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Entity()
@Table()
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
}
