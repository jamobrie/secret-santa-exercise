import java.math.BigDecimal;
import java.util.function.BinaryOperator;

public class BasicExercises2 {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal(123456789.66);
        bigDecimal.toBigInteger().toByteArray();
    }

}
