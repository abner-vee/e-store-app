package app.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private String brand;
    private double price;
    private int quantity;
    private String size;
    private String model;
    private String color;
    private String weight;
    private String expiryDate;
    private String regNo;
    private String vendor;
    private String status;
    private String image;
    private String category;
    private String createdAt;
    private String updatedAt;
}
