package com.debugeandoideas.app_security.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name="roles")
@Data
public class RoleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "role_name", length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "id_customer")
    private Long idCustomer;
}
