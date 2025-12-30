package com.osen.ecommerce.core;


import com.osen.ecommerce.core.category.model.Category;
import com.osen.ecommerce.core.category.service.CategoryService;
import com.osen.ecommerce.core.order.models.Order;
import com.osen.ecommerce.core.order.services.OrderService;
import com.osen.ecommerce.core.product.dtos.CreateProductRequest;
import com.osen.ecommerce.core.product.dtos.UpdateProductRequest;
import com.osen.ecommerce.core.product.mapper.ProductMapper;
import com.osen.ecommerce.core.product.model.Product;
import com.osen.ecommerce.core.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    // QUE PRIMERO REDIRIJA AL PANEL DE ADMIN

    @GetMapping("/admin/products")
    public ResponseEntity<?> listaProductos() {

        List<Product> products = productService.findAll();

        return ResponseEntity.ok(Map.of(
                "products", products,
                "categories", categoryService.findAll()
        ));
        //return "admin/products";
    }

    @GetMapping("/products/new")
    public ResponseEntity<?> mostrarCrearProducto() {

        return ResponseEntity.ok(Map.of(
                "product", new Product(),
                "categories", categoryService.findAll()
        ));

        //return "admin/product-form-new";
    }

    @PostMapping("/products/new")
    public ResponseEntity<?> crearProducto(@RequestBody CreateProductRequest product) {

        productService.save(product);
        log.info("Producto guardado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
        //return "redirect:/admin/products";
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> traerProductoAEditar(@PathVariable Long id) {

        Product product = productService.findById(id);

        return ResponseEntity.ok(Map.of(
                "product", product,
                "categories", categoryService.findAll()
        ));

        //return "admin/product-form-edit";
    }

//    @PutMapping("/products/{id}")
//    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
//
//        Category category = categoryService.findById(request.categoryId());
//        Product product = ProductMapper.toEntity(request, category);
//
//        productService.update(product);
//        return ResponseEntity.noContent().build(); // 204
//    }

    @DeleteMapping("/products/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        productService.deleteById(id);
        return ResponseEntity.accepted().build();

        //return "redirect:/admin/products";
    }

    @GetMapping("/orders")
    public ResponseEntity<?> ordenes() {

        List<Order> orders = orderService.findAll();

        return ResponseEntity.ok(Map.of(
                "orders", orders
        ));
        //return "admin/orders";
    }

//    @PostMapping("/orders/{id}/status")
//    public String cambiarEstadoPedido() {
//
//        return "";
//    }


}
