package com.example.FreshFarm.Delivery.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "farmers_tb")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn
    private User userDetails;
}
