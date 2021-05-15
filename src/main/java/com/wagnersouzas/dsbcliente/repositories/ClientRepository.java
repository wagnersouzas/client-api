package com.wagnersouzas.dsbcliente.repositories;

import com.wagnersouzas.dsbcliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
