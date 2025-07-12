package com.example.modulefacturation.dto;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String siret;
    private LocalDate creationDate;

    public ClientDTO(Long id, String name, String email, String siret, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.siret = siret;
        this.creationDate = creationDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSiret() { return siret; }
    public void setSiret(String siret) { this.siret = siret; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
}