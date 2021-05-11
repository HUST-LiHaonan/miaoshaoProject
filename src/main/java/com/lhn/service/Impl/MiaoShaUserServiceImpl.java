/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: MiaoShaUserServiceImpl
 * Author:   li
 * Date:     2021/5/11 上午9:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.service.Impl;

import com.lhn.dao.MiaoShaUserDao;
import com.lhn.domain.MiaoshaUser;
import com.lhn.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author li
 * @create 2021/5/11
 * @since 1.0.0
 */
@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {
    @Autowired
    private MiaoShaUserDao userDao;

    @Override
    public MiaoshaUser getUserById(String id) {
        MiaoshaUser user = userDao.getUserById(id);
        return user;
    }
}
