package getstarted;

import getstarted.vendor.*;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Test;

import javax.crypto.ExemptionMechanismException;

import static org.junit.Assert.*;

/**
 * Created by twer on 2017/5/25.
 */
public class PosterTest {

    @Tested
    private Poster poster;

    @Injectable
    private MailSender sender;

    @Injectable
    private LDAPService ldapService;

    @Test
    public void should_sent_mail_when_employee_be_found() throws Exception {

        final String EMPLOYEEID = "employeeId";
        new Expectations(){{
            ldapService.fetch(EMPLOYEEID);result=new Employee();
        }};

        final String MSG = "msg";
        poster.sendMail(EMPLOYEEID, MSG);

        new Verifications(){{
            sender.send(anyString, MSG);
        }};

    }


    @Test(expected = EmployeeNotFoundException.class)
    public void should__throws_when_employee_not_found() throws Exception {

        final String employeeId = "employeeId";

        new Expectations(){{
            ldapService.fetch(employeeId);result=null;
        }};

        poster.sendMail(employeeId,"msg");

    }

    @Test(expected = MailErrorException.class)
    public void shold_throw_MailErrorE_when_get_malformatMailE()throws Exception{

        new Expectations(){{
            sender.send(anyString,anyString);result=new MalformedMailException();
        }};

        poster.sendMail("","");
    }
}