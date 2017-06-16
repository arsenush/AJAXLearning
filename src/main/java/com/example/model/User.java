package com.example.model;

import com.example.serializer.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @JsonDeserialize(using = UserSerializer.class)
    private String name;

    @Column
    private Short age;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
