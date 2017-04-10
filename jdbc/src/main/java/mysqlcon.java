/**
 * Created by dmr on 2017/04/06.
 */
import java.sql.ResultSet;

public class mysqlcon {
    public static void main(String args[])
    {
        try {
            JDBCFacade jdbc= new JDBCFacade();
            jdbc.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","000");
            ResultSet rs =  jdbc.executeQuery("select * from user");

            while(rs.next()){
                String email = rs.getString("name") ;
                System.out.println(email);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
