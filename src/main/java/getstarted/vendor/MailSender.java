package getstarted.vendor;

/**
 * Created by twer on 2017/5/24.
 */
public interface MailSender {
    void send(String email, String message)throws MalformedMailException;
}
