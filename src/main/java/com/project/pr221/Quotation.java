package com.project.pr221;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Quotation")
public class Quotation implements Serializable {
    
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      @Column(name = "quotationId", unique = true, nullable = false)
      private long quotationId;
   
      @Column(name = "type")
      private String type;  
      
      public Quotation() {}
    
      public Quotation(String type) {
         this.type = type;
      }

      public long getQuotationId() {
         return quotationId;
      }
    
      public void setQuotationId(long id) {
         this.quotationId = id;
      }
    
      public String getType() {
         return type;
      }
    
      public void setType(String type) {
         this.type = type;
      }
    
      @Override
      public String toString () {
         return this.getQuotationId() + ": " + this.getType();
      }
 }