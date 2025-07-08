package com.agrolink360.productos_service.controller;

import com.agrolink360.productos_service.dto.ActualizarStockDTO;
import com.agrolink360.productos_service.model.Producto;
import com.agrolink360.productos_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    // Crear producto (agricultor)
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    // Ver todos los productos (comprador)
    @GetMapping
    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    // Ver productos por agricultor
    @GetMapping("/agricultor/{nombre}")
    public List<Producto> obtenerPorAgricultor(@PathVariable String nombre) {
        return repository.findByAgricultor(nombre);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/restar-stock")
    public ResponseEntity<String> restarStock(@RequestBody ActualizarStockDTO dto) {
        Optional<Producto> productoOpt = repository.findById(dto.getProductoId());

        if (productoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        Producto producto = productoOpt.get();
        if (producto.getCantidad() < dto.getCantidadRestar()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock insuficiente");
        }

        producto.setCantidad(producto.getCantidad() - dto.getCantidadRestar());
        repository.save(producto);

        return ResponseEntity.ok("Stock actualizado correctamente");
    }





}

