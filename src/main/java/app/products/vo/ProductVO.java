package app.products.vo;

import app.products.enums.Category;
import app.products.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
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
