package com.example.buysell_back.service;

import com.example.buysell_back.model.Image;
import com.example.buysell_back.model.Product;
import com.example.buysell_back.repository.ImageRepository;
import com.example.buysell_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private static final String URL = "http://localhost:8080/product/image/";

    public Product setImage(Product product) {
        product.setImageUrl(URL + product.getId());
        return product;
    }

    public Collection<Product> list() {
        return productRepository.findAll().stream().map(this::setImage).toList();
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Image getImageByProductId(Long id) {
        return imageRepository.findImageByProductId(id).orElse(null);
    }
}
