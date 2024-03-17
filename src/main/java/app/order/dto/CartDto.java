package app.order.dto;

import app.order.vo.CartProductVO;
import app.user.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class CartDto {
    private Integer id;
    private Integer userId;
    private double totalPrice;
    private int quantity;
}
