package servlet;

/**
 * Created by Yonggao.Dong on 16/1/18.
 */
public interface AccountDao {

    boolean isValid(String name, String password);
}
