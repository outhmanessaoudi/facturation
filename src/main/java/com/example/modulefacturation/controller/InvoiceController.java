package com.example.modulefacturation.controller;

import com.example.modulefacturation.dto.InvoiceDTO;
import com.example.modulefacturation.model.Invoice;
import com.example.modulefacturation.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices().stream()
                .map(invoice -> new InvoiceDTO(
                        invoice.getId(),
                        invoice.getClient().getId(),
                        invoice.getDate(),
                        invoice.getLines(),
                        invoice.getTotalHT(),
                        invoice.getTotalTVA(),
                        invoice.getTotalTTC()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> ResponseEntity.ok(new InvoiceDTO(
                        invoice.getId(),
                        invoice.getClient().getId(),
                        invoice.getDate(),
                        invoice.getLines(),
                        invoice.getTotalHT(),
                        invoice.getTotalTVA(),
                        invoice.getTotalTTC()
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        return new InvoiceDTO(
                savedInvoice.getId(),
                savedInvoice.getClient().getId(),
                savedInvoice.getDate(),
                savedInvoice.getLines(),
                savedInvoice.getTotalHT(),
                savedInvoice.getTotalTVA(),
                savedInvoice.getTotalTTC()
        );
    }

    @GetMapping("/client/{clientId}")
    public List<InvoiceDTO> findInvoicesByClient(@PathVariable Long clientId) {
        return invoiceService.findInvoicesByClient(clientId).stream()
                .map(invoice -> new InvoiceDTO(
                        invoice.getId(),
                        invoice.getClient().getId(),
                        invoice.getDate(),
                        invoice.getLines(),
                        invoice.getTotalHT(),
                        invoice.getTotalTVA(),
                        invoice.getTotalTTC()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/date")
    public List<InvoiceDTO> findInvoicesByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return invoiceService.findInvoicesByDateRange(startDate, endDate).stream()
                .map(invoice -> new InvoiceDTO(
                        invoice.getId(),
                        invoice.getClient().getId(),
                        invoice.getDate(),
                        invoice.getLines(),
                        invoice.getTotalHT(),
                        invoice.getTotalTVA(),
                        invoice.getTotalTTC()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/export")
    public ResponseEntity<InvoiceDTO> exportInvoiceToJson(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> ResponseEntity.ok(new InvoiceDTO(
                        invoice.getId(),
                        invoice.getClient().getId(),
                        invoice.getDate(),
                        invoice.getLines(),
                        invoice.getTotalHT(),
                        invoice.getTotalTVA(),
                        invoice.getTotalTTC()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}