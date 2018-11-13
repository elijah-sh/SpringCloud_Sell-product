package com.product.service.impl;

import com.product.common.DecreaseStockInput;
import com.product.common.ProductInfoOutput;
import com.product.dataobject.ProductInfo;
import com.product.enums.ProductStatusEnum;
import com.product.enums.ResultEnum;
import com.product.exception.ProdectException;
import com.product.repository.ProductInfoRepository;
import com.product.service.IProductService;
import com.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 18:56
 * @Description: 商品上下架状态
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn( productIdList).stream()
                .map(e -> {
                    // 数据库查询出来的类型转化成对外暴露的类型
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return  productInfoOutput;
                }).collect(Collectors.toList());
    }

    /**
     * 需要注意的地方 不能当库存减少就发送消息，因为购物车是list可能会抛出异常，
     * 数据库可以回滚，但消息不能，
     * 发送消息
     * @param decreaseStockInputList
     */
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e ->
        {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e,productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
            // 发送消息

            amqpTemplate.convertAndSend("product-exchange",
                    "product.productInfo",
                    JsonUtil.toJson(productInfoOutputList));

    }

    /**
     * 减少库存
     * @param decreaseStockInputList
     * @return
     */
    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();

        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {

            //  Optional 是个容器：它可以保存类型T的值  如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());

            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProdectException(ResultEnum.PRODUCR_NOT_EXIST);
            }
            // 判断 库存是否充足
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProdectException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 扣库存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }


}
