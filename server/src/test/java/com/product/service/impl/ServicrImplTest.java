package com.product.service.impl;

import com.product.ServerApplicationTests;
import com.product.dataobject.ProductInfo;
import com.product.dto.CartDTO;
import com.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:11
 * @Description:
 */
@Component
@Slf4j
public class ServicrImplTest extends ServerApplicationTests {

    @Autowired
    private IProductService productService;
  //  @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() throws Exception {
     /*   List<ProductInfo> list = productService.findList(Arrays.asList("164103465734242707","157875227953464068"));
       log.info("product={}",list);
        Assert.assertTrue(list.size() > 0);*/
    }

    @Test
    public void decreaseStock( ) throws Exception  {
    /*    CartDTO cartDTO = new CartDTO("164103465734242707",2);
        CartDTO cartDTO2 = new CartDTO("157875196366160022",39);
        productService.decreaseStock(Arrays.asList(cartDTO,cartDTO2));*/
    }
}