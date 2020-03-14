package org.dsacleveland.evictiontracker.service.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailSenderService {
    private SendGrid sendGrid;

    @Autowired
    public EmailSenderService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public boolean sendMessage(String email, String message) throws IOException {
        Email from = new Email("donotreply@dsacleveland.org");
        Email to = new Email(email);
        Content content = new Content("text/plain", message);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(new Mail(from, "Automated Message From The Eviction Tracker", to, content).build());

        return sendGrid.api(request).getStatusCode() == 200;
    }
}
