package com.espol.models;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.espol.App;

public class Datos {
    ArrayList<Auspiciante> auspiciantes;
    ArrayList<Emprendedor> emprendedores;
    ArrayList<Feria> ferias;

    public Datos(ArrayList<Auspiciante> auspiciantes, ArrayList<Emprendedor> emprendedores, ArrayList<Feria> ferias) {
        this.auspiciantes = auspiciantes;
        this.emprendedores = emprendedores;
        this.ferias = ferias;
    }

    public static void generarArchivo(Datos datos) {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("archives/datos.dat"))) {
            objOut.writeObject(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Datos leerArchvo(){
        Datos datos = null;
        try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("archives/datos.dat"))){
            datos = (Datos) objIn.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Datos defaultDatos(){
        ArrayList<Auspiciantes> auspiciantes = new ArrayList<>();
        agregarAuspiciantes(auspiciantes);
        ArrayList<Emprendedores> emprendedores = new ArrayList<>();
        agregarEmprendedores(emprendedores);
        ArrayList<Feria> ferias = new ArrayList<>();
        agregarFerias(ferias);
        return new Datos(auspiciantes, emprendedores, ferias);

    }

    public static void agregarEmprendedores(ArrayList<Emprendedores> listaEmprendedores) {
        listaEmprendedores.add(new Emprendedor("1798285937001", "Tienda de Pepe", "0948652474", "tpepe@gmail.com",
                "Urbanización Los Dos Caminos", "www.tiendapepe.com.ec", "Pepe", "Venta de articulos varios"));
        listaEmprendedores.get(0).agregarRedSocial("Twitter", "Pepe", "www.Twitter/pepe");
        listaEmprendedores.get(0).agregarRedSocial("Pinterest", "Pepe", "www.Pinterest/pepe");

        listaEmprendedores
                .add(new Emprendedor("1156183215484", "Super Papeleria", "0995685471", "superpapeleria@gmail.com",
                        "San Marcos, A, El Matacho", "www.superpapel.com.ec", "Maria", "Venta de articulos escolares"));
        listaEmprendedores.get(1).agregarRedSocial("TikTok", "SPapel", "www.TikTok/spapel");
        listaEmprendedores.get(1).agregarRedSocial("LinkedIn", "SPapel", "www.LinkedIn/spapel");

        listaEmprendedores.add(new Emprendedor("1745865631877", "Pasteles del chino", "0985632685",
                "pasteleschino@gmail.com", "Centro Comercial Ctro Com Euro, Piso PB, Local 6-7-8-9",
                "www.pasteles.com.ec", "El Chino", "Venta de pasteles"));
        listaEmprendedores.get(2).agregarRedSocial("LinkedIn", "pchino", "www.LinkedIn/pchino");

        listaEmprendedores.add(new Emprendedor("1456852584174", "Tacos don Jose", "0958714526", "tacostacos@gmail.com",
                "Cl. 20 Y 21, CC El Gran Muro, 2", "www.tacostacos.com.ec", "Jose", "Venta de tacos"));
        listaEmprendedores.get(3).agregarRedSocial("Pinterest", "tjose", "www.Pinterest/tjose");
    }

    public static void agregarAuspiciantes(ArrayList<Auspiciante> listaAuspiciantes) {
        listaAuspiciantes.add(new Auspiciante("1234567891237", "Auspiciante S.A.", "987654321",
                "correoauspiciante@ejemplo.com", "Calle Principal 123", "www.auspiciante.com", "Juan Perez"));
        listaAuspiciantes.get(0).agregarRedSocial("TikTok", "Auspiciantes Oficial",
                "www.tiktok/auspiciantesoficial.com");
        listaAuspiciantes.get(0).agregarSectores(2);
        listaAuspiciantes.get(0).agregarSectores(3);
    }

    public static void agregarFerias(Arrayist<Feria> listaFerias) {
        Feria feria = new Feria(App.listaFerias.size() + 1, "Feria de Navidad",
                "Decoraciones para tu casa en tiempos de Navidad", "Ceibos", "2024-10-20", "2024-12-20", "9:00-20:00");
        feria.asignarNumeroStands(1, 1, 1, 1);
        feria.asignarAuspiciante(listaAuspiciantes.get(0), "Gorros de Navidad", true);
        listaFerias.add(feria);
    }


}
