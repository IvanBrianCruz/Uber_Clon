package uber.clon.uber.Services;



import uber.clon.uber.Models.Conductor;
import uber.clon.uber.Repository.ConductorRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {
    private final ConductorRepository conductorRepository;

    public ConductorService(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    // 🔹 Listar solo conductores activos
    public List<Conductor> listarConductores() {
        return conductorRepository.findByEstadoTrue(); // ✅ Solo activos
    }

    // 🔹 Buscar conductor por ID
    public Optional<Conductor> obtenerConductor(int id) {
        return conductorRepository.findById(id);
    }

    // 🔹 Guardar o actualizar conductor
    public Conductor guardarConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    // 🔹 "Eliminar" (desactivar) un conductor
    public void eliminarConductor(int id) {
        Optional<Conductor> optionalConductor = conductorRepository.findById(id);
        optionalConductor.ifPresent(conductor -> {
            conductor.setEstado(false); // ✅ En lugar de borrar, se desactiva
            conductorRepository.save(conductor);
        });
    }
}