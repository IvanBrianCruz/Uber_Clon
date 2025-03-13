package uber.clon.uber.Services;



import uber.clon.uber.Models.Viaje;
import uber.clon.uber.Repository.ViajeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeService {
    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    // 🔹 Listar solo viajes activos
    public List<Viaje> listarViajes() {
        return viajeRepository.findByEstadoTrue(); // ✅ Solo activos
    }

    // 🔹 Buscar viaje por ID
    public Optional<Viaje> obtenerViaje(Long id) {
        return viajeRepository.findById(id);
    }

    // 🔹 Guardar o actualizar viaje
    public Viaje guardarViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    // 🔹 "Eliminar" (desactivar) un viaje
    public void eliminarViaje(Long id) {
        Optional<Viaje> optionalViaje = viajeRepository.findById(id);
        optionalViaje.ifPresent(viaje -> {
            viaje.setEstado(false); // ✅ Se desactiva en lugar de eliminar
            viajeRepository.save(viaje);
        });
    }
}