package uber.clon.uber.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


import uber.clon.uber.Models.Conductor;
import uber.clon.uber.Models.TipoViaje;
import uber.clon.uber.Models.Viaje;
import uber.clon.uber.Services.ConductorService;
import uber.clon.uber.Services.ViajeService;

@Controller
@RequestMapping("/RideOn")
public class ViajeController {
    private final ViajeService viajeService;
    private final ConductorService conductorService;

    public ViajeController(ViajeService viajeService, ConductorService conductorService) {
        this.viajeService = viajeService;
        this.conductorService = conductorService;
    }

    // Mostrar el formulario de viaje con el conductor preseleccionado o incrustado debajo del formulario de reserva
    @GetMapping("/formReserva/{idConductor}") // id = 1 tiene que ser un numero entero que se le pasa por la url
    public String mostrarFormulario(@PathVariable("idConductor") Integer idConductor, Model model) {
        Conductor conductor = conductorService.obtenerConductor(idConductor);
        if (conductor == null) {
            return "redirect:/conductores?error=ConductorNoEncontrado";
        }

        model.addAttribute("tiposViaje", TipoViaje.values());
        model.addAttribute("conductor", conductor);
        return "solicitarViaje";
    }

    // Procesar el formulario y mostrar el resumen
    @PostMapping("/confirmar-viaje")
    public String confirmarViaje(
            @RequestParam("tipoViaje") TipoViaje tipoViaje,
            @RequestParam("nombreCliente") String nombreCliente,
            @RequestParam("telefonoCliente") String telefonoCliente,
            @RequestParam("puntoPartida") String puntoPartida,
            @RequestParam("destino") String destino,
            @RequestParam("conductorId") Integer conductorId,
            Model model) {
        
        Conductor conductor = conductorService.obtenerConductor(conductorId);
        if (conductor == null) {
            return "redirect:/conductores?error=ConductorNoEncontrado";
        }

        Viaje nuevoViaje = new Viaje(tipoViaje, nombreCliente, telefonoCliente, puntoPartida, destino, conductor);
        viajeService.guardarViaje(nuevoViaje);

        model.addAttribute("viaje", nuevoViaje); // esta variable tiene cargado todos los datos del viaje que se acaba de guardar
        return "resumenViaje";
    }   
}
