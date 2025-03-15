package uber.clon.uber.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uber.clon.uber.Models.Viaje;
import uber.clon.uber.Repository.ViajeRepository;

//import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // Guardar un viaje en la base de datos
    public Viaje guardarViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

   // Buscar un viaje por ID
   public Viaje obtenerViajePorId(Integer id) {
    return viajeRepository.findById(id).orElse(null);
}


    // Listar todos los viajes
    //public List<Viaje> listarViajes() {
    //    return viajeRepository.findAll();
    //}
}
