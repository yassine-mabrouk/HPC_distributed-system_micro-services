package org.enset.entitis;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long  id ;
    private Date billingDate ;
    private long customerID;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    @Transient // attribut n'est pas persistent
    private Customer customer;
}

