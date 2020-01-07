package cn.infomany.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 连接数据库
 * @author: zhanjinbing
 * @data: 2020-01-07 14:18
 */


@Mapper
public interface MyDao {

    @Select("select * from yuansu51 limit 1")
    Object getYuanSu51();
}
