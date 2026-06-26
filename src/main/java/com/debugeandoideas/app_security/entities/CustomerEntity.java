package com.debugeandoideas.app_security.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Table(name="customers")
@Data
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name="pwd")
    private String password;
    @Column(name="rol")
    private String role;

}
