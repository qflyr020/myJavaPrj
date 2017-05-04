/**
 * Created by Administrator on 2017/05/03.
 */
public class IntegerMatch {
    public static void main(String[] args)
    {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }
}
