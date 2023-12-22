package com.example.buysell_back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String description;

    private String imageUrl;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
//    private Image image;
//
//    public void setImage(Image image) {
//        image.setProduct(this);
//        this.image = image;
//    }
}
