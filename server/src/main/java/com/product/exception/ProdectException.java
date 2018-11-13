package com.product.exception;

import com.product.enums.ResultEnum;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/15 16:23
 * @Description:
 */
public class ProdectException extends RuntimeException {

    private Integer code;

    public ProdectException(Integer code , String message){
        super(message);
        this.code  = code;
    }

    public ProdectException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
