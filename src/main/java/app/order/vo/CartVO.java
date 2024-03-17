package app.order.vo;

import app.products.vo.ProductVO;
import app.user.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CartVO {
    private Integer id;
    private UserVO user;
    private List<CartProductVO> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalPrice;
}
