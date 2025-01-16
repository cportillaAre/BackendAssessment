package com.example.backendAssessment.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CheckMortage {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "my_sequence_name", allocationSize = 1)
    private Integer id;

    @Column(precision = 11, scale = 2)
    private BigDecimal income;

    @Column(precision = 11, scale = 2)
    private BigDecimal loanValue;

    @Column(precision = 11, scale = 2)
    private BigDecimal homeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maturity_period", nullable = false)
    private Rates maturityPeriod;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(final BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(final BigDecimal loanValue) {
        this.loanValue = loanValue;
    }

    public BigDecimal getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(final BigDecimal homeValue) {
        this.homeValue = homeValue;
    }

    public Rates getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setMaturityPeriod(final Rates maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }

}
