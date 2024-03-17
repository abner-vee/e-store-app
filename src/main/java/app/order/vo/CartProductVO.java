package app.order.vo;

import app.products.vo.ProductVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CartProductVO {
    private Integer quantity;
    private ProductVO productVO;
}
