package com.example.modulefacturation.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "invoice_lines")
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPriceHT;

    @Column(nullable = false)
    private Double vatRate;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonBackReference
    private Invoice invoice;

    public InvoiceLine() {}

    public InvoiceLine(String description, Integer quantity, Double unitPriceHT, Double vatRate) {
        this.description = description;
        this.quantity = quantity;
        this.unitPriceHT = unitPriceHT;
        this.vatRate = vatRate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getUnitPriceHT() { return unitPriceHT; }
    public void setUnitPriceHT(Double unitPriceHT) { this.unitPriceHT = unitPriceHT; }
    public Double getVatRate() { return vatRate; }
    public void setVatRate(Double vatRate) { this.vatRate = vatRate; }
    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
}
