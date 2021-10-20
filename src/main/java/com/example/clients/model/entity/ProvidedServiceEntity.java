package com.example.clients.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class ProvidedServiceEntity {
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

    @Column
    @JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate date;
}
