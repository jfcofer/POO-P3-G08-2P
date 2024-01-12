package com.espol.models;

import java.time.LocalDate;

public class Stand {
  private String codigo;
  private LocalDate fechaAsignacion;
  private Persona personaAsignada;

  public Stand(String codigo) {
    this.codigo = codigo;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public LocalDate getFechaAsignacion() {
    return this.fechaAsignacion;
  }

  public void setFechaAsignacion(LocalDate fechaAsignacion) {
    this.fechaAsignacion = fechaAsignacion;
  }

  public Persona getPersonaAsignada() {
    return this.personaAsignada;
  }

  public void setPersonaAsignada(Persona personaAsignada) {
    this.personaAsignada = personaAsignada;
  }

  public String toString() {
    return "\nStand:" + "\nCodigo: " + this.codigo + "\nFecha Asignada: " + this.fechaAsignacion
        + "\nPersona Asignada: " + ((this.personaAsignada == null) ? "Ninguna" : personaAsignada.toString());

  }

}