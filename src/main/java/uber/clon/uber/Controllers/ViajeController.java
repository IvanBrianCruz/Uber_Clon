package uber.clon.uber.Controllers;

import uber.clon.uber.Models.Viaje;
import uber.clon.uber.Services.ViajeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/viajes")
public class ViajeController {
    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    // 🔹 Listar solo viajes activos
    @GetMapping
    public String listarViajes(Model model) {
        List<Viaje> viajes = viajeService.listarViajes();
        model.addAttribute("viajes", viajes);
        return "viajes"; // Vista HTML que mostrará la lista
    }

    // 🔹 Mostrar detalles de un viaje específico
    @GetMapping("/{id}")
    public String obtenerViaje(@PathVariable Long id, Model model) {
        Optional<Viaje> viaje = viajeService.obtenerViaje(id);
        if (viaje.isPresent()) {
            model.addAttribute("viaje", viaje.get());
            return "detalleViaje"; // Vista con los detalles del viaje
        }
        return "redirect:/viajes"; // Si no existe, redirige a la lista
    }

    // 🔹 Mostrar formulario para solicitar un viaje
    @GetMapping("/nuevo")
    public String mostrarFormularioViaje(Model model) {
        model.addAttribute("viaje", new Viaje());
        return "formViaje"; // Vista HTML con el formulario
    }

    // 🔹 Guardar un nuevo viaje
    @PostMapping("/guardar")
    public String guardarViaje(@ModelAttribute Viaje viaje) {
        viajeService.guardarViaje(viaje);
        return "redirect:/viajes"; // Redirige a la lista
    }

    // 🔹 "Eliminar" (desactivar) un viaje
    @PostMapping("/eliminar/{id}")
    public String eliminarViaje(@PathVariable Long id) {
        viajeService.eliminarViaje(id); // ✅ Cambia estado a false en lugar de eliminar
        return "redirect:/viajes";
    }
}

