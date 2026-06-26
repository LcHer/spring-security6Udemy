package com.debugeandoideas.app_security.repositories;

import com.debugeandoideas.app_security.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Optional;

@Component
public interface CustomerRepository extends CrudRepository<CustomerEntity, BigInteger> {

    Optional<CustomerEntity> findByEmail(String email);
}
