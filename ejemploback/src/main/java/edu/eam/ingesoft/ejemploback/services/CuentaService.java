package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.model.Transferencia;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public  Cuenta crearCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCedulaCliente()).orElse(null);

        if (cliente == null) {
            throw new RuntimeException("no existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cuenta.getCedulaCliente());

        if (cuentasCliente.size() == 3) {
            throw new RuntimeException("supero el limite de cuentas");
        }

        cuentaRepository.save(cuenta);

        return cuenta;
    }

    public List<Cuenta> listarCuentasCliente(String cedula) {
        return cuentaRepository.buscarCuentasCliente(cedula);
    }

    public Cuenta consignarDinero(Cuenta cuenta){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta.getId()).orElse(null);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuenta.getAmount()<=0) {
            throw new RuntimeException("Valor invalido, no se pueda realizar la transacción");
        }

        double monto = cuentaBD.getAmount() + cuenta.getAmount();

        cuentaBD.setAmount(monto);
        cuentaBD.setId(cuentaBD.getId());
        cuentaBD.setCedulaCliente(cuentaBD.getCedulaCliente());
        cuentaBD.setFechaApertura(cuentaBD.getFechaApertura());

        cuentaRepository.save(cuentaBD);

        Transaccion transaccion = new Transaccion();

        transaccion.setIdTransaccion(Math.random() + "");
        transaccion.setIdCuenta(cuentaBD.getId());
        transaccion.setTipo("Consignación");
        transaccion.setSaldo(cuentaBD.getAmount());


        transaccionRepository.save(transaccion);

        return cuenta;
    }

    public Cuenta retirarDinero(Cuenta cuenta){
        Cuenta cuentaBD = cuentaRepository.findById(cuenta.getId()).orElse(null);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuentaBD.getAmount() == 0 || cuentaBD.getAmount()<cuenta.getAmount()) {
            throw new RuntimeException("No posee fondos suficientes");
        }

        double monto = cuentaBD.getAmount() - cuenta.getAmount();

        cuentaBD.setAmount(monto);
        cuentaBD.setId(cuentaBD.getId());
        cuentaBD.setCedulaCliente(cuentaBD.getCedulaCliente());
        cuentaBD.setFechaApertura(cuentaBD.getFechaApertura());

        cuentaRepository.save(cuentaBD);

        Transaccion transaccion = new Transaccion();

        transaccion.setIdTransaccion(Math.random() + "");
        transaccion.setIdCuenta(cuentaBD.getId());
        transaccion.setTipo("Retiro");
        transaccion.setSaldo(cuentaBD.getAmount());

        transaccionRepository.save(transaccion);

        return cuenta;
    }

    public void eliminarCuenta(String id) {
        Cuenta cuentaBD = cuentaRepository.getOne(id);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuentaBD.getAmount()!=0) {
            throw new RuntimeException("Saldo en cuenta, no se puede cancelar");
        }

        cuentaRepository.deleteById(id);
    }

    public Transferencia transferir(Transferencia transferencia){
        Cuenta cuentaBD = cuentaRepository.findById(transferencia.getCuenta1()).orElse(null);
        Cuenta cuentaBD2 = cuentaRepository.findById(transferencia.getCuenta2()).orElse(null);

        if (cuentaBD == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuentaBD2 == null) {
            throw new RuntimeException("No existe la cuenta");
        }

        if (cuentaBD.getAmount() == 0 || cuentaBD.getAmount()<transferencia.getMonto()) {
            throw new RuntimeException("No posee fondos suficientes");
        }

        double monto = cuentaBD.getAmount() - transferencia.getMonto();
        double monto2 = cuentaBD2.getAmount() + transferencia.getMonto();

        cuentaBD.setAmount(monto);
        cuentaBD.setId(cuentaBD.getId());
        cuentaBD.setCedulaCliente(cuentaBD.getCedulaCliente());
        cuentaBD.setFechaApertura(cuentaBD.getFechaApertura());

        cuentaRepository.save(cuentaBD);

        cuentaBD2.setAmount(monto2);
        cuentaBD2.setId(cuentaBD2.getId());
        cuentaBD2.setCedulaCliente(cuentaBD2.getCedulaCliente());
        cuentaBD2.setFechaApertura(cuentaBD2.getFechaApertura());

        cuentaRepository.save(cuentaBD2);
//////////////////////////////////////////////////////////////////
        Transaccion transaccion = new Transaccion();

        transaccion.setIdTransaccion(Math.random() + "");
        transaccion.setIdCuenta(cuentaBD.getId());
        transaccion.setTipo("Transferencia");
        transaccion.setSaldo(cuentaBD.getAmount());

        transaccionRepository.save(transaccion);

////////////////////////////////////////////////////////////////
        transaccion.setIdTransaccion(Math.random() + "");
        transaccion.setIdCuenta(cuentaBD2.getId());
        transaccion.setTipo("Consignación");
        transaccion.setSaldo(cuentaBD2.getAmount());

        transaccionRepository.save(transaccion);


        return transferencia;
    }
}
