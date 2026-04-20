package com.finantech.controller;

import com.finantech.model.Transaccion;
import com.finantech.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> listar() {
        return transaccionService.listarTodas();
    }

    @GetMapping("/{id}")
    public Transaccion obtener(@PathVariable Long id) {
        return transaccionService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Transaccion> crear(@RequestBody Transaccion transaccion) {
        Transaccion nueva = transaccionService.guardar(transaccion);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizar(@PathVariable Long id, @RequestBody Transaccion transaccion) {
        Transaccion existente = transaccionService.obtenerPorId(id);
        existente.setMonto(transaccion.getMonto());
        existente.setEstado(transaccion.getEstado());
        existente.setDescripcion(transaccion.getDescripcion());
        existente.setFecha(transaccion.getFecha());
        return ResponseEntity.ok(transaccionService.guardar(existente));
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    transaccionService.eliminar(id);
    return ResponseEntity.ok(Map.of(
        "mensaje", "Transacción eliminada correctamente",
        "id", id,
        "status", "OK"
    ));
}

    @GetMapping("/fallidas")
    public List<Transaccion> fallidas() {
        return transaccionService.obtenerFallidas();
    }
}