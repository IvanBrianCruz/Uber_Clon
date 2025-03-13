package uber.clon.uber.Models;


import jakarta.persistence.*;

@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoViaje tipo;

    private double costo;// revisar y modificar el tipo de dato
    private String nombreCliente;
    private String telefonoCliente;
    private String puntoPartida;
    private String destino;

    private boolean estado = true; // ✅ Agregado (por defecto en true)

    @OneToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    // 🔹 Constructor vacío
    public Viaje() {}

    // 🔹 Constructor con parámetros
    public Viaje(TipoViaje tipo, double costo, String nombreCliente, String telefonoCliente, String puntoPartida, String destino, Conductor conductor, boolean estado) {
        this.tipo = tipo;
        this.costo = costo;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.puntoPartida = puntoPartida;
        this.destino = destino;
        this.conductor = conductor;
        this.estado = true; // ✅ Siempre inicia activo
    }

    // 🔹 Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoViaje getTipo() { return tipo; }
    public void setTipo(TipoViaje tipo) { this.tipo = tipo; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getTelefonoCliente() { return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }

    public String getPuntoPartida() { return puntoPartida; }
    public void setPuntoPartida(String puntoPartida) { this.puntoPartida = puntoPartida; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Conductor getConductor() { return conductor; }
    public void setConductor(Conductor conductor) { this.conductor = conductor; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}