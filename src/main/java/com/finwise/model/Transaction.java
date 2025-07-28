package com.finwise.model;

import com.finwise.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tipo de transação é obrigatório")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal amount;

    private String category;

    private String description;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
