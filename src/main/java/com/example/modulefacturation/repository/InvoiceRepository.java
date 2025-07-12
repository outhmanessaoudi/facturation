package com.example.modulefacturation.repository;

import com.example.modulefacturation.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByClientId(Long clientId);
    List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);
}