package com.sp.model.vo;
import com.sp.model.domain.ShoppingCart;
import lombok.Data;

import java.io.Serializable;


/**
 * 订单提交vo
 *
 * @author 罗汉
 * @date 2024/07/23
 */
@Data
public class OrdersSubmitVO implements Serializable {
    private Long userId;
    private Long businessId;
    private Long tableId;
//    private ShoppingCart shoppingCart;
}
