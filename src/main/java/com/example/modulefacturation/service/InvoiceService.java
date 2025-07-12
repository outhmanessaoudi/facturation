package com.example.modulefacturation.service;

import com.example.modulefacturation.model.Invoice;
import com.example.modulefacturation.model.InvoiceLine;
import com.example.modulefacturation.model.Client;
import com.example.modulefacturation.repository.ClientRepository;
import com.example.modulefacturation.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private static final List<Double> VALID_VAT_RATES = Arrays.asList(0.0, 5.5, 10.0, 20.0);

    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        Long clientId = invoice.getClient().getId();
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client non trouvé avec l'ID : " + clientId);
        }
        if (invoice.getLines().isEmpty()) {
            throw new IllegalArgumentException("Une facture doit avoir au moins une ligne.");
        }
        for (InvoiceLine line : invoice.getLines()) {
            if (line.getDescription() == null || line.getQuantity() == null || line.getUnitPriceHT() == null || line.getVatRate() == null) {
                throw new IllegalArgumentException("Tous les champs des lignes doivent être remplis.");
            }
            if (!VALID_VAT_RATES.contains(line.getVatRate())) {
                throw new IllegalArgumentException("Taux de TVA invalide : " + line.getVatRate());
            }
            line.setInvoice(invoice);
        }
        invoice.setDate(LocalDate.now());
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findInvoicesByClient(Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }

    public List<Invoice> findInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByDateBetween(startDate, endDate);
    }
}