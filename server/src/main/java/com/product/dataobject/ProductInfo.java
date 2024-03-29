package com.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 15:40
 * @Description:
 */
@Data
@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    private String productId;

    /* 名字 */
    private String productName;

    /* 单价 */
    private BigDecimal productPrice;

    /* 库存 */
    private Integer productStock;

    /* 描述 */
    private String productDescription;

    /* 小图 */
    private String productIcon;

    /* 状态 0 正常 1 下架  */
    private Integer productStatus;

    /* 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
