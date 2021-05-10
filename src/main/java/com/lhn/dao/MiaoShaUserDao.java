/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaUserDao
 * Author:   mac
 * Date:     2021/5/10 8:38 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.dao;

import com.lhn.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/10
 * @since 1.0.0
 */
@Mapper
public interface MiaoShaUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    MiaoshaUser getUserById(Long id);
}
