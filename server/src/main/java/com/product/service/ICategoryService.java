package com.product.service;

import com.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:17
 * @Description:
 */
public interface ICategoryService {
    /**
     *  通过类型 查询所有商品分类
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
