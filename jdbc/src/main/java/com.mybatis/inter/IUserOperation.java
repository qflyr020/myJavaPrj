package com.mybatis.inter;

import java.util.List;

import com.mybatis.model.Article;
import com.mybatis.model.User;

public interface IUserOperation {
    //通过id查询user信息
    //注：方法名 selectUserByID 必须与 User.xml 里面配置的 select 的id 对应
    //（<select id="selectUserByID"）
    public User selectUserByID(int id);

    //返回user列表
    public List<User> selectUsers(String userName);

    //增加user
    public void addUser(User user);

    //更新user
    public void updateUser(User user);

    //通过id删除数据
    public void deleteUser(int id);

    public void deleteUser(String userName);

    //通过id查询文章getUserArticles
    public List<Article> getUserArticles(int id);


}
