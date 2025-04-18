package com.example.shopapp.services;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.dtos.ProductImageDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.exceptions.InvalidParamException;
import com.example.shopapp.models.Product;
import com.example.shopapp.models.ProductImage;
import com.example.shopapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface IProducService {
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(Long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    Product updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    boolean existsByName(String name);
    ProductImage createProductImage(Long productId,
                                    ProductImageDTO productImageDTO) throws DataNotFoundException, InvalidParamException;

}
