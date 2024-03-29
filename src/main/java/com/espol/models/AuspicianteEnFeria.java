package com.espol.models;

public class AuspicianteEnFeria extends Auspiciante {
    private String descripcion;
    private boolean tieneStand;

    public AuspicianteEnFeria(Auspiciante auspiciante, String descripcion, boolean tieneStand) {
        super(auspiciante.getRuc(), auspiciante.getNombre(), auspiciante.getTelefono(), auspiciante.getEmail(),
                auspiciante.getDireccion(), auspiciante.getSitioWeb(), auspiciante.getPersonaResponsable());
        this.descripcion = descripcion;
        this.tieneStand = tieneStand;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcionServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getTieneStand() {
        return tieneStand;
    }

    public void setTieneStand(boolean tieneStand) {
        this.tieneStand = tieneStand;
    }

    public String toString() {
        return "\nAuspiciante: " + super.toString() + "\nDescripcion del Servicio: " + descripcion
                + "\nTiene Stand: " + (tieneStand ? "Si" : "No");
    }

}
