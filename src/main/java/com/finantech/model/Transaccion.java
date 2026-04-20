package com.finantech.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    private Double monto;
    private String estado;
    private String descripcion;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("transacciones")
    private Cliente cliente;
}