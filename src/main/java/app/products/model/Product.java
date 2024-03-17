package app.products.model;

import app.common.model.BaseModel;
import app.products.enums.Category;
import app.products.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product extends BaseModel{
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
    private Status status;
    private String image;
    private Category category;
}
