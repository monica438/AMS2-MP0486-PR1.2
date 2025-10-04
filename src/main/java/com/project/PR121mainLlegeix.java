package com.project;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR121hashmap;

public class PR121mainLlegeix {
    private static String filePath = System.getProperty("user.dir") + "/data/PR121HashMapData.ser";

    public static void main(String[] args) {
        try {
            PR121hashmap hashMap = deserialitzarHashMap();
            hashMap.getPersones().forEach((nom, edat) -> System.out.println(nom + ": " + edat + " anys"));
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error al llegir l'arxiu: " + e.getMessage());
        }
    }

    public static PR121hashmap deserialitzarHashMap() throws IOFitxerExcepcio {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (PR121hashmap) ois.readObject();

        // Aquesta excepció es llança si hi ha un problema a l'hora de llegir l'objecte (ja sigui perquè el fitxer no existeix o està corrupte)
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en deserialitzar l'objecte HashMap", e);
        } catch (ClassNotFoundException e) {
            throw new IOFitxerExcepcio("Classe no trobada durant la deserialització", e);
        }
    }

    // Getter
    public static String getFilePath() {
        return filePath;
    }

    // Setter
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }    
}