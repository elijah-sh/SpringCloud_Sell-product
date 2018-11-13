package com.product.service.impl;

import com.product.ServerApplicationTests;
import com.product.dataobject.ProductCategory;
import com.product.service.ICategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:22
 * @Description:
 */
@Component
public class CategoryServiceImplTest extends ServerApplicationTests {

    @Autowired
    private ICategoryService categoryService;
    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(list.size() > 0);
    }
}