package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Organization {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "organization_id_seq",
            sequenceName = "organization_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "organization_id_seq"
    )
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "scopus_id", unique = true)
    private Long scopusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    private Sector sector;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScopusId() {
        return scopusId;
    }

    public void setScopusId(Long scopusId) {
        this.scopusId = scopusId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(scopusId, that.scopusId) && Objects.equals(country, that.country) && Objects.equals(sector, that.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, scopusId, country, sector);
    }
}
