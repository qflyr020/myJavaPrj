/**
 * Created by Administrator on 2017/04/07.
 */
public class Main {
    public static void main(String[] args) {
       ShowObject<Dog> showdog=new ShowObject<Dog>();
       showdog.ShowMess(new Dog());

       ShowObject<Cat> showcat=new ShowObject<Cat>();
       showcat.ShowMess(new Cat());
    }
}


