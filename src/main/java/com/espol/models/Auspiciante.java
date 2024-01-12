package com.espol.models;

import java.util.ArrayList;

public class Auspiciante extends Persona {
  private ArrayList<Sectores> lstSectores;

  // Constructor de un objeto Auspiciante
  public Auspiciante(String ruc, String nombre, String telefono, String email, String direccion, String sitioWeb,
      String personaResponsable) {
    super(ruc, nombre, telefono, email, direccion, sitioWeb, personaResponsable);
    this.lstSectores = new ArrayList<Sectores>();
  }

  public String toString() {
    return super.toString() + "\nSectores: " + lstSectores;
  }

  public ArrayList<Sectores> getLstTipoSectores() {
    return this.lstSectores;
  }

  public void borrarSectores() {
    this.lstSectores = new ArrayList<Sectores>();
  }

  public void agregarSectores(int opcion) {
    if (opcion == 1) {
      this.lstSectores.add(Sectores.ALIMENTACION);
    } else if (opcion == 2) {
      this.lstSectores.add(Sectores.EDUCACION);
    } else if (opcion == 3) {
      this.lstSectores.add(Sectores.SALUD);
    } else if (opcion == 4) {
      this.lstSectores.add(Sectores.VESTIMENTA);
    }
  }
  
  @Override
    public boolean equals(Object obj) {
        Auspiciante a = (Auspiciante)obj;
        if (this.getNombre().equals(a.getNombre()))
            return true;
        return false;
    }
}