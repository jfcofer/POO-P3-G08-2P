package com.espol.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Seccion implements Serializable{
  private int id;
  private ArrayList<Stand> stands;

  public Seccion(int id, ArrayList<Stand> stands) {
    this.id = id;
    this.stands = stands;
  }

  public Seccion(int id) {
    this.id = id;
    this.stands = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public ArrayList<Stand> getStands() {
    return this.stands;
  }

  public void addStand(String codigo) {
    this.stands.add(new Stand(codigo));
  }

  public String toString() {
    return "Seccion: Id - " + this.id + ", Lista de Stands - "
        + this.stands;
  }

}