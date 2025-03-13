package uber.clon.uber.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uber.clon.uber.Models.Conductor;
import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    List<Conductor> findByEstadoTrue(); // ✅ Solo devuelve conductores activos
}