package uber.clon.uber.Services;


import uber.clon.uber.Models.Conductor;
import uber.clon.uber.Repository.ConductorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;

    //public ConductorService(ConductorRepository conductorRepository) {
    //    this.conductorRepository = conductorRepository;
    //}

    // 🔹 Listar solo conductores treu = verdadero 
    public List<Conductor> listarConductores() {
        return conductorRepository.findByEstadoTrue(); // ✅ Solo verdadero 
    }

    // 🔹 Buscar conductor por ID
    public Conductor obtenerConductor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        return conductorRepository.findById(id).orElse(null);
    }

    // 🔹 crear un coductor 
    public Conductor guardarConductor(Conductor conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo no puede venir en blanco.");
        }
        return conductorRepository.save(conductor);
    }

    // 🔹 "Eliminar" (desactivar) un conductor
    public Conductor eliminarConductor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        Conductor conductor = conductorRepository.findById(id).orElse(null);
        if (conductor != null) {
            conductor.setEstado(false);  
            return conductorRepository.save(conductor);
        }
        return null;  
    }
}