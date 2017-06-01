package getstarted;


import getstarted.some_service.ADao;
import getstarted.some_service.BDao;
import getstarted.some_service.MailSender;

/**
 *
 * Created by Yonggao.Dong on 16/9/13.
 */

public class SomeService {

    private ADao aDao;
    private BDao bDao;


    public void doSome(){
        aDao.update();
        bDao.delete();

        MailSender.sendMail();
    }
}
