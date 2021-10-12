package com.example.clients.model.repository;

import com.example.clients.model.entity.ServiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {    
}
