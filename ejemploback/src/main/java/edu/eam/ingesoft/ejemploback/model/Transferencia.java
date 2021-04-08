package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import java.io.Serializable;

public class Transferencia implements Serializable {

    private String cuenta1;
    private String cuenta2;
    private double monto;

    public Transferencia() {
    }

    public Transferencia(String cuenta1, String cuenta2, double monto) {
        this.cuenta1 = cuenta1;
        this.cuenta2 = cuenta2;
        this.monto = monto;
    }

    public String getCuenta1() {
        return cuenta1;
    }

    public void setCuenta1(String cuenta1) {
        this.cuenta1 = cuenta1;
    }

    public String getCuenta2() {
        return cuenta2;
    }

    public void setCuenta2(String cuenta2) {
        this.cuenta2 = cuenta2;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
