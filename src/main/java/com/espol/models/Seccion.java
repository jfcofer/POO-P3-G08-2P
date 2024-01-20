package com.espol.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Seccion implements Serializable {
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

  public void modifyStandsNumber(int originalCount, int newCount) {
    if (originalCount < newCount) {
      addStands(newCount - originalCount);
    } else if (originalCount > newCount) {
      removeStands(originalCount - newCount);
    }
  }

  public void addStands(int n) {

    String prefix = null;

    switch (id) {
      case 1:
        prefix = "A";
        break;
      case 2:
        prefix = "B";
        break;
      case 3:
        prefix = "C";
        break;
      case 4:
        prefix = "D";
        break;
    }

    for (int i = stands.size(); i < stands.size() + n; i++) {
      addStand(prefix + i);
    }

  }

  public void removeStands(int n) {
    for (int i = 0; i < n; i++)
      stands.removeLast();
  }

  public String toString() {
    return "Seccion: Id - " + this.id + ", Lista de Stands - "
        + this.stands;
  }

}