/**
 * Copyright (C), 2016-2021, 华中科技大学
 * FileName: UserUtil
 * Author:   mac
 * Date:     2021/5/25 1:04 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lhn.utils;

import com.alibaba.fastjson.JSONObject;
import com.lhn.domain.MiaoshaUser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author mac
 * @create 2021/5/25
 * @since 1.0.0
 */
public class UserUtil {
    private void createUser(int count) throws Exception{
        List<MiaoshaUser> users = new ArrayList<>(count);
        //生成用户
        for(int i=0;i<count;i++) {
            MiaoshaUser user = new MiaoshaUser();
            user.setId(String.valueOf(13000000000L+i));
            user.setLoginCount(1);
            user.setNickname("user"+i);
            user.setRegisterDate(new Date());
            user.setSalt("1q2w3e4r5t");
            String formPassWord = MD5Util.inputPassWordToFormPassWord("123456");
            user.setPassword(MD5Util.formPassWordToDbPassWord(formPassWord, user.getSalt()));
            users.add(user);
        }
        /*System.out.println("create user");
		//插入数据库
		Connection conn = DBUtil.getConn();
		String sql = "insert into miaosha_user(login_count, nickname, register_date, salt, password, id)values(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i=0;i<users.size();i++) {
			MiaoshaUser user = users.get(i);
			pstmt.setInt(1, user.getLoginCount());
			pstmt.setString(2, user.getNickname());
			pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getId());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.close();
		conn.close();
		System.out.println("insert to db");*/
        //登录，生成token
        String urlString = "http://localhost:8080/login/do_login";
        File file = new File("/Users/mac/Desktop/tokens.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            MiaoshaUser user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection)url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassWordToFormPassWord("123456");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jo = JSON.parseObject(response);
            String token = jo.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId()+","+token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }

    public static void main(String[] args) throws Exception {
        UserUtil userUtil = new UserUtil();
        userUtil.createUser(5000);
    }

}
