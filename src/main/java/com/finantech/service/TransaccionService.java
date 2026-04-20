package com.finantech.service;

import com.finantech.model.Transaccion;
import com.finantech.model.Cliente;
import com.finantech.repository.TransaccionRepository;
import com.finantech.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ClienteRepository clienteRepository; 

    public List<Transaccion> listarTodas() {
        return transaccionRepository.findAllByOrderByIdTransaccionAsc();
    }

    public Transaccion obtenerPorId(Long id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
    }

    public Transaccion guardar(Transaccion transaccion) {

        // 🔥 Validar cliente
        if (transaccion.getCliente() == null || transaccion.getCliente().getId() == null) {
            throw new RuntimeException("Debe enviar el ID del cliente");
        }

        // 🔥 Buscar cliente real en BD
        Cliente cliente = clienteRepository.findById(transaccion.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 🔥 Asignar cliente completo
        transaccion.setCliente(cliente);

        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> obtenerFallidas() {
        return transaccionRepository.findByEstado("FALLIDA");
    }

    public void eliminar(Long id) {
        transaccionRepository.deleteById(id);
    }
}