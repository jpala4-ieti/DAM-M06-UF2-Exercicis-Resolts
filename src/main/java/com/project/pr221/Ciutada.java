package com.project.pr221;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Ciutada")
public class Ciutada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ciutadaId", unique = true, nullable = false)
    private long ciutadaId;

    @Column(name = "ciutatId")
    private long ciutatId;
   
    @Column(name = "nom")
    private String nom;  

    @Column(name = "cognom")
    private String cognom;

    @Column(name = "edat")
    private int edat;

    public Ciutada() {}
    
    public Ciutada(long ciutatId, String nom, String cognom, int edat) {
        this.ciutatId = ciutatId;
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }

    public long getCiutadaId () {
        return this.ciutadaId;
    }

    public void setCiutadaId (long ciutadaId) {
        this.ciutadaId = ciutadaId;
    }

    public long getCiutatId () {
        return this.ciutatId;
    }

    public void setCiutatId (long ciutatId) {
        this.ciutatId = ciutatId;
    }

    public String getNom () {
        return this.nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }  

    public String getCognom () {
        return this.cognom;
    }

    public void setCognom (String cognom) {
        this.cognom = cognom;
    }  

    public int getEdat () {
        return this.edat;
    }

    public void setEdat (int edat) {
        this.edat = edat;
    }  

    @Override
    public String toString () {
        return this.nom + " " + this.cognom + ", " + this.edat + " - " + this.ciutatId;
    }
}
