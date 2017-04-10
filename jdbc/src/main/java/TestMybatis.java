import java.io.Reader;
import java.util.List;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mybatis.inter.IUserOperation;
import com.mybatis.model.Article;
import com.mybatis.model.User;

public class TestMybatis {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    //得到user列表
    public void getUserList(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation  userOperation = session.getMapper(IUserOperation.class);
            List<User> users = userOperation.selectUsers(userName);
            for(User user : users) {
                System.out.println(user.getUserName()+","+user.getUserAge());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //测试增加,增加后，必须提交事务，否则不会写入到数据库.
    @Test
    public void addUser() {
        User user = new User();
        user.setId(5);
        user.setUserName("wangjiuyin");
        user.setUserAge("23");
        user.setUserAddress("henanzhongyixueyuan");
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("当前增加的用户的ID：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //更新user信息
    public void updateUser() {
        //先得到用户，然后修改，提交
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(4);
            user.setUserName("wjy");
            user.setUserAddress("shangqiu");
            userOperation.updateUser(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //删除user数据
    public void deleteUser(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            userOperation.deleteUser(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //删除user数据 增加，更改，删除的时候要调用session.commit()，
    //这样才会真正对数据库进行操作，否则是没有提交的。
    public void deleteUser(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            userOperation.deleteUser(userName);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //多对一的实现：得到用户与文章
    public void getUserArticles(int userid){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            List<Article> articles = userOperation.getUserArticles(userid);
            for(Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                        ":作者是:"+article.getUser().getUserName()+":地址:"+
                        article.getUser().getUserAddress());
            }
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
/*		//第一种测试：
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("com.mybatis.models.UserMapper.selectUserByID",1);
			System.out.println(user.getUserName());
			System.out.println(user.getUserAge());
			System.out.println(user.getUserAddress());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}*/

		//第二种测试：
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(1);
			System.out.println(user.getUserName());
			System.out.println(user.getUserAge());
			System.out.println(user.getUserAddress());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		/*//第三种测试：查询user列表
		TestMybatis test = new TestMybatis();
		//test.getUserList("%");
		test.getUserList("%");*/

		/*//第四种测试：增加user
		TestMybatis test = new TestMybatis();
		test.addUser();*/

		/*//第六种测试：通过id删除user
		TestMybatis test = new TestMybatis();
		test.deleteUser(4);*/

		/*//第八种测试：通过userName删除user
		TestMybatis test = new TestMybatis();
		test.deleteUser("wjy");*/

/*        //第九种测试：多对一的实现
        TestMybatis test = new TestMybatis();
        test.getUserArticles(2);*/
    }
}
