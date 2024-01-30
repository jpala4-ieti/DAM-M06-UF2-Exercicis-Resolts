package com.project.pr221;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Ciutat")
public class Ciutat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ciutatId", unique = true, nullable = false)
    private long ciutatId;
   
    @Column(name = "nom")
    private String nom;  

    @Column(name = "pais")
    private String pais;

    @Column(name = "codiPostal")
    private int codiPostal;

    public Ciutat() {}
    
    public Ciutat(String nom, String pais, int codiPostal) {
       this.nom = nom;
       this.pais = pais;
       this.codiPostal = codiPostal;
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

    public String getPais () {
        return this.pais;
    }

    public void setPais (String pais) {
        this.pais = pais;
    }  

    public int getCodiPostal () {
        return this.codiPostal;
    }

    public void setCodiPostal (int codiPostal) {
        this.codiPostal = codiPostal;
    }  

    @Override
    public String toString () {
        return this.nom + ", " + this.pais + " " + this.codiPostal;
    }
}
