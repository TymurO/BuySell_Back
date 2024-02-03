package com.example.buysell_back.service;

import com.example.buysell_back.model.Image;
import com.example.buysell_back.model.Product;
import com.example.buysell_back.repository.ImageRepository;
import com.example.buysell_back.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void productService_list_returnListOfTwoProducts() {
        Product productOne =  Product.builder()
                .name("Table")
                .price(15000.0)
                .description("Black wooden table")
                .imageUrl("http://localhost:8080/image/1")
                .build();
        Product productTwo =  Product.builder()
                .name("Cap")
                .price(100.0)
                .description("White cap")
                .imageUrl("http://localhost:8080/image/2")
                .build();

        given(productRepository.findAll()).willReturn(List.of(productOne, productTwo));

        List<Product> list = productService.list().stream().toList();

        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void productService_getImageById_returnImage() {
        Long id = 1L;

        given(imageRepository.findImageByProductId(id)).willReturn(Optional.of(new Image()));

        assertThat(productService.getImageByProductId(id)).isNotNull();
    }

    @Test
    void productService_getImageById_returnNull() {
        Long id = 1L;

        given(imageRepository.findImageByProductId(id)).willReturn(Optional.empty());

        assertThat(productService.getImageByProductId(id)).isNull();
    }
}
