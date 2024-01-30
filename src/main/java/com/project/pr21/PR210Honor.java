package com.project.pr21;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PR210Honor {

    static Scanner in = new Scanner(System.in); 
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "database-forhonor.db";
        boolean running = true;

        File fDatabase = new File(filePath);
        if (!fDatabase.exists()) { initDatabase(filePath); }

        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);

        while (running) {
            String txt =  ""
                        + "\n1) Mostrar taula"
                        + "\n2) Mostrar personatges"
                        + "\n3) Mostrar millor atacant"
                        + "\n4) Mostrar millor defensant"
                        + "\n5) Sortir";
            System.out.println(txt);
            int valor = Integer.parseInt(readLine("Escull una opció: "));

            switch (valor) {
                case 1: mostrarTaula(conn); break;
                case 2: mostrarPersonatges(conn, "tots"); break;
                case 3: mostrarPersonatges(conn, "atacant"); break;
                case 4: mostrarPersonatges(conn, "defensant"); break;
                case 5: running = false; break;
                default:
            }  
        }

        // Desconnectar
        UtilsSQLite.disconnect(conn);
    }

    static String readLine (String text) {
        System.out.print(text);
        return in.nextLine();
    }

    static void initDatabase (String filePath) {
        // Connectar (crea la BBDD si no existeix)
        Connection conn = UtilsSQLite.connect(filePath);

        // Esborrar les taules (per si cas)
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS Faccio;");
        UtilsSQLite.queryUpdate(conn, "DROP TABLE IF EXISTS Personatge;");

        // Crear les taules
        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS Faccio ("
                                    + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + "	nom VARCHAR(15) NOT NULL,"
                                    + "	resum VARCHAR(500));");

        UtilsSQLite.queryUpdate(conn, "CREATE TABLE IF NOT EXISTS Personatge ("
                                    + "	id integer PRIMARY KEY AUTOINCREMENT,"
                                    + "	nom VARCHAR(15) NOT NULL,"
                                    + "	atac REAL NOT NULL,"
                                    + "	defensa REAL NOT NULL,"
                                    + "	idFaccio INTEGER NOT NULL, FOREIGN KEY (idFaccio) REFERENCES Faccio (id));");
 
        // Afegir files
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Faccio (nom, resum) VALUES (\"Cavallers\", \"Though seen as a single group, the Knights are hardly unified. There are many Legions in Ashfeld, the most prominent being The Iron Legion.\");");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Faccio (nom, resum) VALUES (\"Vikings\",   \"The Vikings are a loose coalition of hundreds of clans and tribes, the most powerful being The Warborn.\");");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Faccio (nom, resum) VALUES (\"Samurais\",  \"The Samurai are the most unified of the three factions, though this does not say much as the Daimyos were often battling each other for dominance.\");");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Warden\",      1, 3, 1);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Conqueror\",   2, 2, 1);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Peacekeep\",   2, 3, 1);");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Raider\",    3, 3, 2);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Warlord\",   2, 2, 2);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Berserker\", 1, 1, 2);");

        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Kensei\",  3, 2, 3);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Shugoki\", 2, 1, 3);");
        UtilsSQLite.queryUpdate(conn, "INSERT INTO Personatge (nom, atac, defensa, idFaccio) VALUES (\"Orochi\",  3, 2, 3);");
 
        UtilsSQLite.disconnect(conn);
    }

    static void mostrarTaula(Connection conn) {

        // Demanar quina taula
        ArrayList<String> taules = UtilsSQLite.listTables(conn);
        String taula = ""; 
        while (taules.indexOf(taula) == -1) {
            taula = readLine("Quina taula [" + String.join(", ", taules) + "]? ");
        } 

        // Mostra la taula que correspon
        try {
            System.out.println("Contingut de la taula '" + taula + "':");
            if (taula.compareTo("Faccio") == 0) {
                ResultSet rs = UtilsSQLite.querySelect(conn, "SELECT * FROM Faccio;");
                while (rs.next()) {
                    System.out.println("  id:  " + rs.getInt("id")
                                     + ",\tnom: " + rs.getString("nom")
                                     + ",\tresum: " + rs.getString("resum").substring(0, 25) + "...");
                }
            } else if (taula.compareTo("Personatge") == 0) {
                ResultSet rs = UtilsSQLite.querySelect(conn,  "SELECT Personatge.id, Personatge.nom, "
                                                            + "Personatge.atac, Personatge.defensa, "
                                                            + "Faccio.nom AS nomFaccio "
                                                            + "FROM Personatge JOIN Faccio "
                                                            + "ON Personatge.idFaccio = Faccio.id;");
                while (rs.next()) {
                    System.out.println("  id:  " + rs.getInt("id")
                                     + ",\tnom: " + rs.getString("nom")
                                     + ",\tatac: " + rs.getFloat("atac")
                                     + ",\tdefensa: " + rs.getFloat("defensa")
                                     + ",\tfaccio: " + rs.getString("nomFaccio"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    static void mostrarPersonatges(Connection conn, String tipus) {
        try {
            ArrayList<String> faccions = getNomsFaccions(conn);

            // Demanar quina faccio
            String faccio = "";
            while (faccions.indexOf(faccio) == -1) {
                faccio = readLine("Quina facció [" + String.join(", ", faccions) + "]? ");
            } 

            // Mostra personatges de la facció
            System.out.println("Personatges de la facció '" + faccio + "':");
            String query = "";
            if (tipus.compareTo("tots") == 0) {
                query = "SELECT Personatge.id, Personatge.nom, "
                      + "Personatge.atac, Personatge.defensa, "
                      + "Faccio.nom AS nomFaccio "
                      + "FROM Personatge JOIN Faccio "
                      + "ON Personatge.idFaccio = Faccio.id AND Faccio.nom=\"" + faccio + "\";";
            } else if (tipus.compareTo("atacant") == 0) {
                query = "SELECT Personatge.id, Personatge.nom, "
                      + "MAX(Personatge.atac) AS atac, Personatge.defensa, "
                      + "Faccio.nom AS nomFaccio "
                      + "FROM Personatge JOIN Faccio "
                      + "ON Personatge.idFaccio = Faccio.id AND Faccio.nom=\"" + faccio + "\";";
            } else if (tipus.compareTo("defensant") == 0) {
                query = "SELECT Personatge.id, Personatge.nom, "
                      + "Personatge.atac, MAX(Personatge.defensa) AS defensa, "
                      + "Faccio.nom AS nomFaccio "
                      + "FROM Personatge JOIN Faccio "
                      + "ON Personatge.idFaccio = Faccio.id AND Faccio.nom=\"" + faccio + "\";";
            }

            // Mostrar dades
            ResultSet rs = UtilsSQLite.querySelect(conn, query);
            while (rs.next()) {
                System.out.println("  id:  " + rs.getInt("id")
                                + ",\tnom: " + rs.getString("nom")
                                + ",\tatac: " + rs.getFloat("atac")
                                + ",\tdefensa: " + rs.getFloat("defensa")
                                + ",\tfaccio: " + rs.getString("nomFaccio"));
            }
        } catch (SQLException e) { e.printStackTrace(); }        
    }

    static ArrayList<String> getNomsFaccions (Connection conn) {
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet rs = UtilsSQLite.querySelect(conn, "SELECT nom FROM Faccio;");
            while (rs.next()) { result.add(rs.getString(1)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return result;
    }
}