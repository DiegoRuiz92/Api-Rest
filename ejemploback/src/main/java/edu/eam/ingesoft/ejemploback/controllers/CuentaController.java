package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transferencia;
import edu.eam.ingesoft.ejemploback.services.CuentaService;
import edu.eam.ingesoft.ejemploback.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/accounts")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.crearCuenta(cuenta);
    }

    @GetMapping("/customers/{cedula}/accounts")
    public List<Cuenta> listarCuentasCliente(@PathVariable String cedula) {
        return cuentaService.listarCuentasCliente(cedula);
    }
    @PutMapping("/accounts/consignar")
    public Cuenta consignar(@RequestBody Cuenta cuenta){
        return cuentaService.consignarDinero(cuenta);
    }

    @PutMapping("/accounts/retirar")
    public Cuenta retirar(@RequestBody Cuenta cuenta){ return cuentaService.retirarDinero(cuenta);
    }

    @PutMapping("/accounts/transferir")
    public Transferencia transferir(@RequestBody Transferencia transferencia){ return cuentaService.transferir(transferencia);
    }

    @DeleteMapping("/accounts/{id}")
    public void eliminar(@PathVariable String id) {
       cuentaService.eliminarCuenta(id);
    }

}
