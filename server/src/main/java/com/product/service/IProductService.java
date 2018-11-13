package com.product.service;

import com.product.common.DecreaseStockInput;
import com.product.common.ProductInfoOutput;
import com.product.dataobject.ProductInfo;
import com.product.dto.CartDTO;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 18:50
 * @Description:
 */

public interface IProductService {
    /**
     *  查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 通过商品id来查找 商品
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     *  减少库存
     * @Param  cartDTOList 购物车商品列表
     */
    void  decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
