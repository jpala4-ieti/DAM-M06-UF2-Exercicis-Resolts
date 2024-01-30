package com.project.pr24;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Llibre", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "llibreId")})
public class Llibre implements Serializable {

	@Id
	@Column(name = "llibreId", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY) // L'id es genera automàticament
	private long llibreId;

	@Column(name = "nom")
	private String nom;

	@Column(name = "editorial")
	private String editorial;

	@ManyToMany(mappedBy = "llibres")   
	private Set<Biblioteca> biblioteques; 

	@ManyToMany(mappedBy = "llibres")   
	private Set<Persona> persones; 

	@ManyToOne
    @JoinColumn(name = "autorId", insertable = false, updatable = false)
    private Autor Autor;

	public Llibre() { }

	public Llibre(String nom, String editorial) {
		this.nom = nom;
		this.editorial = editorial;
	}

	public long getLlibreId() {
		return this.llibreId;
	}

	public void setLlibreId(long id) {
		this.llibreId = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
    public String toString () {
      	return this.getLlibreId() + ": " + this.getNom() + ", " + this.getEditorial();
    }
}