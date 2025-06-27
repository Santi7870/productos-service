package com.agrolink360.productos_service.repository;

import com.agrolink360.productos_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional; // ✅ IMPORTACIÓN NECESARIA

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByAgricultor(String agricultor);

    Optional<Producto> findById(Long id);
}


