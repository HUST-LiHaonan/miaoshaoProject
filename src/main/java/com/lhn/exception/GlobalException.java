/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: GlobleException
 * Author:   li
 * Date:     2021/5/11 上午11:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.exception;

import com.lhn.dto.CodeMsg;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author li
 * @create 2021/5/11
 * @since 1.0.0
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
