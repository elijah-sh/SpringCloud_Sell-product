package com.product.controller;

import com.product.common.DecreaseStockInput;
import com.product.common.ProductInfoOutput;
import com.product.dataobject.ProductInfo;
import com.product.dto.CartDTO;
import com.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/12 09:58
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ServiceController {

    @Autowired
    private IProductService productService;
    @GetMapping("/msg")
    public String getMessage(){
        return "hello product!!!  -  2";
    }

    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {

      /*  try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return productService.findList(productIdList);
    }

    /**
     * 扣库存
     * @param decreaseStockInputList [{"productId":"157875196366160022","productQuantity":1}]
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList ) {
          productService.decreaseStock(decreaseStockInputList);
    }
}
