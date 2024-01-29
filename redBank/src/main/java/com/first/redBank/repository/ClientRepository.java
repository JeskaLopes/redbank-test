package com.first.redBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.first.redBank.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	 Client findByCpfCnpj(String cpfCnpj);
}

