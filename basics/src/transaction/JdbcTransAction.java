/**
 * Created by Administrator on 2017/04/17.
 */
import  java.sql.*;
public class JdbcTransAction {
    private Connection conn = null;
    private Statement statement = null;

    public  void conn(){
        try {
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","000");
           conn.setAutoCommit(false);
           statement=conn.createStatement();
           statement.execute("UPDATE user set userAge= userAge + 7 where id=1 ");
           //int i= Integer.parseInt(" 0.01");
           statement.execute("UPDATE user set userAge= userAge - 5 where id=1 ");
           conn.commit();
        }
        catch (SQLException sqle)
        {
            try {
                conn.rollback();
                statement.close();
                conn.close();
            }catch (Exception ex) {

            }
            sqle.printStackTrace();
        }
        catch (Exception ex)
        {

        }
    }
}
class Mainc {
    public static void main(String[] args) {
       JdbcTransAction jdbcTransAction=new JdbcTransAction();
       jdbcTransAction.conn();
    }
}
