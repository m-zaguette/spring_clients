package com.example.clients.model.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientEntity client;

    @Column
    private BigDecimal value;
}
