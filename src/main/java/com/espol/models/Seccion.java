package com.espol.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Seccion implements Serializable{
  private int id;
  private ArrayList<Stand> lstStands;

  public Seccion(int id, ArrayList<Stand> lstStands) {
    this.id = id;
    this.lstStands = lstStands;
  }

  public Seccion(int id) {
    this.id = id;
    this.lstStands = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public ArrayList<Stand> getLstStands() {
    return this.lstStands;
  }

  public void addStand(String codigo) {
    this.lstStands.add(new Stand(codigo));
  }

  public String toString() {
    return "Seccion: Id - " + this.id + ", Lista de Stands - "
        + this.lstStands;
  }

}