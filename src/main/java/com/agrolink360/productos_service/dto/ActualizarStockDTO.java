package com.agrolink360.productos_service.dto;

import lombok.Data;

@Data
public class ActualizarStockDTO {
    private Long productoId;
    private int cantidadRestar;

}

