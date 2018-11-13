package com.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:42
 * @Description:
 */
@Data
public class ProductInfoVO {

    /**
     *  "id": "123456",
     *        "name": "皮蛋粥",
     *      "price": 1.2,
     *      "description": "好吃的皮蛋粥",
     *        "icon": "http://xxx.com",
     */

    @JsonProperty("id")
    private String productId;

    /* 名字 */
    @JsonProperty("name")
    private String productName;

    /* 单价 */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /* 描述 */
    @JsonProperty("description")
    private String productDescription;

    /* 小图 */
    @JsonProperty("icon")
    private String productIcon;

}
