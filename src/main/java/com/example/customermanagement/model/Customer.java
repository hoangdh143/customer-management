package com.example.customermanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Length(max = 100)
    private String name;

    private String phone;

    private String email;

    private String company;

    private String address;

    private Integer birthYear;

    private Long income;

    public Customer() {
    }

    public Customer(@Length(max = 100) String name, String phone, String email, String company, String address, Integer birthYear, Long income) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.company = company;
        this.address = address;
        this.birthYear = birthYear;
        this.income = income;
    }
}
