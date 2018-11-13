package com.product.controller;

import com.product.dataobject.ProductCategory;
import com.product.dataobject.ProductInfo;
import com.product.service.ICategoryService;
import com.product.service.IProductService;
import com.product.utils.ResultVOUtil;
import com.product.vo.ProductInfoVO;
import com.product.vo.ProductVO;
import com.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 15:37
 * @Description: 商品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    /**
     * 1. 查询所有在架商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @RequestMapping("/list")
    public ResultVO<ProductVO> list(){
        //1. 查询所有在架的商品
      List<ProductInfo> productInfoList = productService.findUpAll();
        //2. 获取类目type列表
      List<Integer> categoryTypeList = productInfoList.stream()
              .map(ProductInfo::getCategoryType)
              .collect(Collectors.toList());
        // 3. 查询类目
      List<ProductCategory>  categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 4. 构造数据
       List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList ) {
            ProductVO productVO = new ProductVO(); // 商品视图对象
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);  //将productInfo数据转存到 productInfoVO中 先get 再 set
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);

    }
}
