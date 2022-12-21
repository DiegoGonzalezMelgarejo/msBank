package com.bank.diego.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Persona implements Serializable {
    @Id
    @Column(name = "cliente_id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Column(name = "clave",nullable = false)
    private String clave;

    @Column(name = "estado",nullable = false)
    private Boolean estado;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Cuenta> cuentaList = new ArrayList<>();
}
