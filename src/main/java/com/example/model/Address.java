package com.example.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String street;
}
