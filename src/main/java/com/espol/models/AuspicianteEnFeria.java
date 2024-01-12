package com.espol.models;

public class AuspicianteEnFeria {
    private Auspiciante auspiciante;
    private String descripcion;
    private boolean tieneStand;

    public AuspicianteEnFeria(Auspiciante auspiciante, String descripcion, boolean tieneStand) {
        this.auspiciante = auspiciante;
        this.descripcion = descripcion;
        this.tieneStand = tieneStand;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(Auspiciante auspiciante) {
        this.auspiciante = auspiciante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcionServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isTieneStand() {
        return tieneStand;
    }

    public void setTieneStand(boolean tieneStand) {
        this.tieneStand = tieneStand;
    }

    public String toString() {
        return "\nAuspiciante: " + auspiciante.toString() + "\nDescripcion del Servicio: " + descripcion
                + "\nTiene Stand: " + (tieneStand ? "Si" : "No");
    }
    
    @Override
    public boolean equals(Object obj) {
        AuspicianteEnFeria a = (AuspicianteEnFeria)obj;
        if (this.auspiciante.equals(a.getAuspiciante()))
            return true;
        return false;
    }

}
