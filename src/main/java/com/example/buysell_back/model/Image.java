package com.example.buysell_back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;
}
