package com.example.backendAssessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rates {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    private Integer maturityPeriod;

    @Column(precision = 9, scale = 4)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private OffsetDateTime lastUpdate;

    @OneToMany(mappedBy = "maturityPeriod")
    private Set<CheckMortage> maturityPeriodCheckMortages;

    public Rates(Integer maturityPeriod, BigDecimal interestRate, OffsetDateTime lastUpdate) {
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdate = lastUpdate;
    }

    public Integer getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setMaturityPeriod(final Integer maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(final BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(final OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<CheckMortage> getMaturityPeriodCheckMortages() {
        return maturityPeriodCheckMortages;
    }

    public void setMaturityPeriodCheckMortages(
        final Set<CheckMortage> maturityPeriodCheckMortages) {
        this.maturityPeriodCheckMortages = maturityPeriodCheckMortages;
    }

}
