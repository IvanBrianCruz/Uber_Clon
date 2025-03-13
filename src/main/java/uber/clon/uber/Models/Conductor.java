package uber.clon.uber.Models;

import uber.clon.uber.Models.TipoAutomovil;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private String automovil;

    @Enumerated(EnumType.STRING)
    private TipoAutomovil tipoAutomovil;
    // uno a uno 
    @OneToOne(mappedBy = "conductor", cascade = CascadeType.ALL)
    private Viaje viaje;
    private boolean estado = true; // ✅ Agregado (por defecto en true)

    // 🔹 Constructor vacío
    public Conductor() {}

    // 🔹 Constructor con parámetros
    public Conductor(String nombre, String apellido, LocalDate fechaDeNacimiento, String automovil, TipoAutomovil tipoAutomovil, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.automovil = automovil;
        this.tipoAutomovil = tipoAutomovil;
        this.estado=estado; // ✅ Siempre inicia activo
    }

    // 🔹 Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public String getAutomovil() { return automovil; }
    public void setAutomovil(String automovil) { this.automovil = automovil; }

    public TipoAutomovil getTipoAutomovil() { return tipoAutomovil; }
    public void setTipoAutomovil(TipoAutomovil tipoAutomovil) { this.tipoAutomovil = tipoAutomovil; }

    public Viaje getViaje() { return viaje; }
    public void setViaje(Viaje viaje) { this.viaje = viaje; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}