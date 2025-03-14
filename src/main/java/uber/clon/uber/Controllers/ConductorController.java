package uber.clon.uber.Controllers;

import uber.clon.uber.Models.Conductor;
import uber.clon.uber.Services.ConductorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/conductores")
public class ConductorController {
    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    // 🔹 Mostrar lista de conductores activos
    @GetMapping
    public String listarConductores(Model model) {
        List<Conductor> conductores = conductorService.listarConductores();
        model.addAttribute("conductores", conductores);
        return "index"; // Vista HTML
    }

    // 🔹 Mostrar formulario de registro
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "formConductor"; // Vista HTML para crear un conductor
    }

    // 🔹 Guardar nuevo conductor
    @PostMapping("/guardar")
    public String registrarConductor(@ModelAttribute Conductor conductor) {
        conductorService.guardarConductor(conductor);
        return "redirect:/conductores"; // Redirige a la lista
    }

    // 🔹 "Eliminar" (desactivar) un conductor
    @PostMapping("/eliminar/{id}")
    public String eliminarConductor(@PathVariable int id) {
        conductorService.eliminarConductor(id); // Se desactiva (estado = false)
        return "redirect:/conductores"; // Redirige a la lista
    }

    // 🔹 Mostrar detalles de un conductor
    @GetMapping("/detalle/{id}")
    public String mostrarDetallesConductor(@PathVariable int id, Model model) {
        // Llamamos al servicio para obtener el conductor
        Conductor conductor = conductorService.obtenerConductor(id);

        if (conductor == null) {
            return "redirect:/conductores"; // Si no existe, vuelve a la lista
        }

        int edad = Period.between(conductor.getFechaDeNacimiento(), LocalDate.now()).getYears();
        model.addAttribute("edad", edad); // Agregar edad calculada

        model.addAttribute("conductor", conductor);
        return "detalleConductor"; // Vista de detalles
    }

}
