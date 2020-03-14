package org.dsacleveland.evictiontracker.service.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@XSlf4j
@Profile("!local-dev")
public class EmailSenderServiceImpl implements EmailSenderService {
    private SendGrid sendGrid;

    @Autowired
    public EmailSenderServiceImpl(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public boolean sendMessage(String email, String message) {
        Email from = new Email("donotreply@dsacleveland.org");
        Email to = new Email(email);
        Content content = new Content("text/plain", message);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");

        try {
            request.setBody(new Mail(from, "Automated Message From The Eviction Tracker", to, content).build());
            return sendGrid.api(request).getStatusCode() == 200;
        } catch (IOException e) {
            log.error("Could not send email " + message + " to email address " + email
                    + ", exception " + e.getMessage());
            return false;
        }
    }
}
