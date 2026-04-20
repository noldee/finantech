package com.finantech.repository;

import com.finantech.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByEstado(String estado);

    List<Transaccion> findByClienteId(Long clienteId);

    List<Transaccion> findAllByOrderByIdTransaccionAsc(); 
}