package com.product.vo;

import lombok.Data;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/10 19:30
 * @Description: http 请求返回的最外层对象
 */
@Data
public class ResultVO<T> {

    /* 错误码 */
    private Integer code;

    /* 提示信息*/
    private String msg;

    /* 具体的数据 */
    private T data;


}
