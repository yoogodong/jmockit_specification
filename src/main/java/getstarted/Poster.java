package getstarted;


import getstarted.vendor.*;

import javax.inject.Inject;

/**
 * Created by twer on 2017/5/25.
 */
public final class Poster
{
    @Inject
    private LDAPService ldapService;

    @Inject
    private MailSender sender;

    public void sendMail(String employeeId, String msg) throws EmployeeNotFoundException, MailErrorException {
        Employee employee = ldapService.fetch(employeeId);

        if (employee==null) throw new EmployeeNotFoundException();

        try {
            sender.send(employee.getMail(),msg);
        } catch (MalformedMailException e) {
            throw new MailErrorException();
        }
    }
}

