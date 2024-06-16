package Libreria.objetos;

import java.time.LocalDate;

public class Cita {
    private String email;
    private LocalDate fecha;

    public Cita(String email, LocalDate fecha) {
        this.email = email;
        this.fecha = fecha;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}