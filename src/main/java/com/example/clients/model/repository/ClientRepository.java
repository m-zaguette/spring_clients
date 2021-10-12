package com.example.clients.model.repository;

import com.example.clients.model.entity.ClientEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <ClientEntity, Integer> {
}
