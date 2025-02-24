package com.halisaha.field.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FieldType type;

    @NotNull
    @Positive
    private Integer capacity;

    @NotNull
    @Positive
    private BigDecimal basePrice;

    @NotNull
    @Positive
    private BigDecimal peakHourPrice;

    private String description;

    private String features;

    @NotNull
    private Boolean isActive = true;
} 