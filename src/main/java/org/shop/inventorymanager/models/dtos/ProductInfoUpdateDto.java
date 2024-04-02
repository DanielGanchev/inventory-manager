package org.shop.inventorymanager.models.dtos;

public record ProductInfoUpdateDto(Long id,String name, String description, double price, String category) {

}
