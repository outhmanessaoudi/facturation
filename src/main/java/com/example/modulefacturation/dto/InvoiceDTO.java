package com.example.modulefacturation.dto;

import com.example.modulefacturation.model.InvoiceLine;

import java.time.LocalDate;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Long clientId;
    private LocalDate date;
    private List<InvoiceLine> lines;
    private double totalHT;
    private double totalTVA;
    private double totalTTC;

    public InvoiceDTO(Long id, Long clientId, LocalDate date, List<InvoiceLine> lines, double totalHT, double totalTVA, double totalTTC) {
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.lines = lines;
        this.totalHT = totalHT;
        this.totalTVA = totalTVA;
        this.totalTTC = totalTTC;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<InvoiceLine> getLines() { return lines; }
    public void setLines(List<InvoiceLine> lines) { this.lines = lines; }
    public double getTotalHT() { return totalHT; }
    public void setTotalHT(double totalHT) { this.totalHT = totalHT; }
    public double getTotalTVA() { return totalTVA; }
    public void setTotalTVA(double totalTVA) { this.totalTVA = totalTVA; }
    public double getTotalTTC() { return totalTTC; }
    public void setTotalTTC(double totalTTC) { this.totalTTC = totalTTC; }
}