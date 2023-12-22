package com.example.buysell_back.controller;

import com.example.buysell_back.model.Image;
import com.example.buysell_back.model.Product;
import com.example.buysell_back.model.Response;
import com.example.buysell_back.service.ProductService;
import com.example.buysell_back.util.ImageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Response> getProducts() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("products", productService.list()))
                        .message("Products retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveProduct(@RequestPart("product") String productJson,
                                                @RequestPart("photo") MultipartFile photo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJson, Product.class);
        Image image = ImageUtils.toImageFromFile(photo);
        image.setProduct(product);
        System.out.println(image);
        System.out.println(product);
        productService.save(image);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data was saved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

//    @PostMapping("/save")
//    public ResponseEntity<Response> saveProduct(@RequestBody Product product) throws IOException {
//        productService.saveProduct(product);
//        return ResponseEntity.ok(
//                Response.builder()
//                        .timeStamp(LocalDateTime.now())
//                        .message("Data was saved")
//                        .status(HttpStatus.OK)
//                        .statusCode(HttpStatus.OK.value())
//                        .build()
//        );
//    }

    @GetMapping(path = "/image/{id}", produces = IMAGE_PNG_VALUE)
    public byte[] getProductImage(@PathVariable("id") Long id) {
        Image image = productService.getImageByProductId(id);
        if (image != null) {
            System.out.println(image);
            return ImageUtils.decompressImage(image.getImageData());
        }
        return null;
    }
}
