package com.example.clients.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{field.name.obrigatory}")
    private String name;

    @Column(nullable = false, length = 11)
    @NotNull (message = "{field.cpf.obrigatory}")
    @CPF (message = "{field.cpf.invalid}")
    private String cpf;

    @Column(name = "signIn_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate signInDate;

    @PrePersist
    public void prePersist(){
        setSignInDate(LocalDate.now());
    }
}
