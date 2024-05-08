package com.iText.IText.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class BankTransaction {

    private LocalDate Date;

    private String Description;

    private Double Amount;

    private Double Balance;
}
