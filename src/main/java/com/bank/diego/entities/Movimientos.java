package com.bank.diego.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "movimientos")
@Data
public class Movimientos implements Serializable {
    @Id
    @Column(name = "movimiento_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoId;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    @Column(name = "valor")
    private Long valor;

    @Column(name = "saldo")
    private Long saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}
