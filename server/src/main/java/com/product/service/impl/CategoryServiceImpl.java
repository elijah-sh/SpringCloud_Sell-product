package com.product.service.impl;

import com.product.dataobject.ProductCategory;
import com.product.repository.ProductCategoryRepository;
import com.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:20
 * @Description:
 */
@Service
public class CategoryServiceImpl implements ICategoryService  {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
