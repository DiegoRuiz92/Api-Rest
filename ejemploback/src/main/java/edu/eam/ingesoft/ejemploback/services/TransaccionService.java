package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public TransaccionRepository transaccionRepository;

    @Autowired
    public CuentaRepository cuentaRepository;

     /*public List<Transaccion> listarTransacciones(String id) {
        return transaccionRepository.buscarTransaccion(id);
    }*/

}
