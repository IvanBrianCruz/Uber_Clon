package uber.clon.uber.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uber.clon.uber.Models.Viaje;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    List<Viaje> findByEstadoTrue(); // ✅ Solo devuelve viajes activos
}