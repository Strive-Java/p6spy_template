package cn.infomany.controller;

import cn.infomany.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 测试p6spy是否可以运行
 * @author: zhanjinbing
 * @data: 2020-01-07 14:14
 */
@Controller
public class MyHello {

    @Autowired
    private MyDao myDao;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Object yuansu = myDao.getYuanSu51();
        System.out.println(yuansu);
        return "hello world";
    }

}
