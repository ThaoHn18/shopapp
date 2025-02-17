package com.example.shopapp.controller;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.dtos.ProductImageDTO;
import com.example.shopapp.models.Product;
import com.example.shopapp.models.ProductImage;
import com.example.shopapp.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jsoup.internal.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
//@Validated
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("")
    public ResponseEntity<String> getAllProducts(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "limit") int limit
    ) {
        return ResponseEntity.ok("getproducts");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String productId) {
        return ResponseEntity.ok("procductId: "+ productId);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try{

            Product newProduct = productService.createProduct(productDTO);
            return ResponseEntity.ok(newProduct);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(@PathVariable("id") Long id,
                                        @ModelAttribute("files") List<MultipartFile> files,
                                        BindingResult result
    ) {
        try {
            Product existingProduct = productService.getProductById(id);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            List<ProductImage> productImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {

                    if (file.getSize() > 10 * 1024 * 1024) {
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too big");
                    }
                    String ContenType = file.getContentType();
                    if (ContenType == null || ContenType.startsWith("image/")) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is not image");
                    }

                }
                if (file.getSize() == 0) {
                    continue;
                }
                // luu file va cap nhat thumbai dto
                String fileName = storeFile(file);
                // luu xuong DB
                ProductImage productImage = productService.createProductImage(existingProduct.getId(),
                        ProductImageDTO.builder()
                                .productId(existingProduct.getId())
                                .imageUrl(fileName)
                                .build()
                );
            }


            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);

            }

            return ResponseEntity.ok("tao thanh cong");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // them uid truoc ten file de dam bao ten file la duy nhta
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            // duong dan den thu muc muon luu:
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            // duong dan day du:
            Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
            // sao chep vao thu muc dich:
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok("xoa thanh cong");
    }

}
