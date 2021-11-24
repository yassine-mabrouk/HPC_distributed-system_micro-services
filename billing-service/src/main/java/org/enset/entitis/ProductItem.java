package org.enset.entitis;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private int quantity ;
    private double price;
    private long productID;
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Bill bill ;
    @Transient
    private Product product ;

}
