package expect_exception;

/**
 * Created by twer on 2017/5/31.
 */
public class Factorial {

    public int factorial(int i) throws InvalidInputException {
        if (i==1) return 1;
        if (i>10 || i<1) throw new InvalidInputException();
        return i*factorial(i--);
    }
}
