/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaKey
 * Author:   mac
 * Date:     2021/5/27 1:34 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.key;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/27
 * @since 1.0.0
 */
public class MiaoShaKey extends BasePrefix{

    public MiaoShaKey(String prefix, Integer expireSeconds) {
        super(prefix, expireSeconds);
    }

    public MiaoShaKey(String prefix) {
        super(prefix);
    }

    public static MiaoShaKey isGoodsOver = new MiaoShaKey("go");
    public static MiaoShaKey getMiaoshaPath = new MiaoShaKey( "mp",60);
    public static MiaoShaKey getMiaoshaVerifyCode = new MiaoShaKey( "vc",300);
}
