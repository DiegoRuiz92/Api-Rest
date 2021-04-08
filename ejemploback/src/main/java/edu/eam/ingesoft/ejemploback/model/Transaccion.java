package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//Entidad que representa la tabla transactions
@Entity
@Table(name="transactions")

public class Transaccion implements Serializable {
    @Id
    @Column(name = "transactionid")
    private String idTransaccion;

    @Column(name = "accountid")
    private String idCuenta;

    @Column(name = "type")
    private String tipo;

    @Column(name = "amount")
    private double saldo;

    @Column(name = "transactiondate")
    private Date fechaTransaccion;

    public Transaccion() {
        fechaTransaccion = new Date();
    }

    public Transaccion(String idTransaccion, String idCuenta, String tipo, double saldo, Date fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fechaTransaccion = fechaTransaccion;
    }


    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
