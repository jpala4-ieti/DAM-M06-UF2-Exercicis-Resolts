package com.project.pr24;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Persona", 
   uniqueConstraints = {@UniqueConstraint(columnNames = "personaId")})
public class Persona implements Serializable {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "personaId", unique = true, nullable = false)
        private long personaId;

        @Column(name = "dni")
        private String dni; 

        @Column(name = "nom")
        private String nom; 

        @Column(name = "telefon")
        private String telefon; 
      
        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(name = "Llibre_persona", 
            joinColumns = {@JoinColumn(referencedColumnName = "personaId")}, 
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "llibreId")})
        private Set<Llibre> llibres; 

        public Persona() {}
    
        public Persona(String dni, String nom, String telefon) {
            this.dni = dni;
            this.nom = nom;
            this.telefon = telefon;
        }

        public long getPersonaId() {
            return personaId;
        }
    
        public void setPersonaId(long id) {
            this.personaId = id;
        }

        public String getDni() {
            return dni;
        }
   
        public void setDni(String dni) {
            this.dni = dni;
        }
    
        public String getNom() {
            return nom;
        }
    
        public void setNom(String nom) {
            this.nom = nom;
        }
    
        public String getTelefon() {
            return telefon;
        }
    
        public void setTelefon(String telefon) {
            this.telefon = telefon;
        }

        public Set<Llibre> getLlibres () {
		    return this.llibres;
	    }

        public void setLlibres (Set<Llibre> llibres) {
            this.llibres = llibres;
        }

        public List<Object[]> queryLlibres () {
            long id = this.getPersonaId();
            return Manager.queryTable("SELECT DISTINCT l.* FROM Llibre_persona lp, Llibre l WHERE l.llibreId = lp.llibres_llibreId AND lp.persones_personaId = " + id);
        }

        @Override
        public String toString () {
            String str = Manager.tableToString(queryLlibres()).replaceAll("\n", " | ");
            return this.getPersonaId() + ": " + this.getNom() + ", " + this.getTelefon() + ", Llibres: [" + str + "]";
        }
 }