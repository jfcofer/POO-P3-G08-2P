package com.espol.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Feria {
    private int codigo;
    private String nombre;
    private String descripcion;
    private String lugar;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String horario;
    private ArrayList<AuspicianteEnFeria> auspiciantes;
    private ArrayList<Emprendedor> emprendedores;
    private Seccion[] secciones;

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = LocalDate.parse(fechaInicio);
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = LocalDate.parse(fechaFin);
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ArrayList<AuspicianteEnFeria> getAuspiciantes() {
        return auspiciantes;
    }

    public void setAuspiciantes(ArrayList<AuspicianteEnFeria> auspiciantes) {
        this.auspiciantes = auspiciantes;
    }

    public ArrayList<Emprendedor> getEmprendedores() {
        return emprendedores;
    }

    public void setEmprendedores(ArrayList<Emprendedor> emprendedores) {
        this.emprendedores = emprendedores;
    }

    public Seccion[] getSecciones() {
        return secciones;
    }

    public void setSecciones(Seccion[] secciones) {
        this.secciones = secciones;
    }

    public Feria(int codigo, String nombre, String descripcion, String lugar, String fechaInicio, String fechaFin,
            String horario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaInicio = LocalDate.parse(fechaInicio);
        this.fechaFin = LocalDate.parse(fechaFin);
        this.horario = horario;
        this.secciones = new Seccion[4];
        for (int i = 0; i < 4; i++) {
            secciones[i] = new Seccion(i + 1);
        }
        this.auspiciantes = new ArrayList<>();
        this.emprendedores = new ArrayList<>();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nLugar: " + lugar
                + "\nFecha de Inicio: " + fechaInicio.toString() + "\nFecha de Fin: " + fechaFin.toString()
                + "\nHorario" + horario
                + "\nAusipciantes:");
        for (AuspicianteEnFeria auspiciante : auspiciantes) {
            str.append(auspiciante.toString());
        }
        str.append("\nEmprendedores:");
        for (Emprendedor emprendedor : emprendedores) {
            str.append(emprendedor.toString());
        }
        return str.toString();
    }

    public void asignarAuspiciante(Auspiciante auspiciante, String descripcion, boolean tieneStand) {
        this.auspiciantes.add(new AuspicianteEnFeria(auspiciante, descripcion, tieneStand));
    }

    public String distribucionStands() {
        StringBuilder str = new StringBuilder();
        for (Seccion seccion : secciones) {
            str.append("\nSeccion " + seccion.getId() + "\n");
            for (Stand stand : seccion.getLstStands()) {
                String mensaje = (stand.getPersonaAsignada() != null) ? "*" : "";
                str.append("[" + stand.getCodigo() + mensaje + "]");
            }
        }
        return str.toString();
    }

    public String consultarEmprendedores() {
        StringBuilder str = new StringBuilder();
        for (Emprendedor emprendedor : emprendedores) {
            str.append("\n" + emprendedor.toString());
        }
        return str.toString();
    }

    public void reservarStand(String codigoStand, Persona persona, LocalDate date) {
        for (Seccion seccion : secciones) {
            for (Stand stand : seccion.getLstStands()) {
                if (stand.getCodigo().equals(codigoStand)) {
                    stand.setFechaAsignacion(date);
                    stand.setPersonaAsignada(persona);
                }
            }
        }
    }

    public void asignarNumeroStands(int st1, int st2, int st3, int st4) {
        String[] letrasCodigo = { "A", "B", "C", "D" };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < st1; j++) {
                String codigo = letrasCodigo[i] + (j + 1);
                secciones[i].addStand(codigo);
            }
        }
    }

    public String informacionStand(String codigoStand) {
        for (Seccion seccion : this.secciones) {
            for (Stand stand : seccion.getLstStands()) {
                if (stand.getCodigo().equals(codigoStand)) {
                    return stand.toString();
                }
            }
        }
        return null;
    }

    public static Feria buscarFeria(int codigo, ArrayList<Feria> ferias) {
        for (Feria feria : ferias) {
            if (feria.getCodigo() == codigo) {
                return feria;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feria other = (Feria) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
}
