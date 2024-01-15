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

    private static final String URL = "http://13.53.130.193/product/image/";

    public Product setImage(Product product) {
        product.setImageUrl(URL + product.getId());
        return product;
    }

    public Collection<Product> list() {
        return productRepository.findAll().stream().map(this::setImage).toList();
    }

    public Product save(Image image) {
        return setImage(imageRepository.save(image).getProduct());
    }

    public Image getImageByProductId(Long id) {
        return imageRepository.findImageByProductId(id).orElse(null);
    }

    public Boolean delete(Long id) {
        imageRepository.deleteImageByProductId(id);
        return Boolean.TRUE;
    }
}
