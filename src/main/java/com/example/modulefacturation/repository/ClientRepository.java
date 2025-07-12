package com.example.modulefacturation.repository;

import com.example.modulefacturation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}