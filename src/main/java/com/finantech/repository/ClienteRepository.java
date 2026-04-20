package com.finantech.repository;

import com.finantech.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);

    List<Cliente> findAllByOrderByIdAsc(); 
}