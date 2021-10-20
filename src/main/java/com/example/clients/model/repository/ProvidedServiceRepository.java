package com.example.clients.model.repository;

import com.example.clients.model.entity.ProvidedServiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidedServiceRepository extends JpaRepository<ProvidedServiceEntity, Integer> {
}
