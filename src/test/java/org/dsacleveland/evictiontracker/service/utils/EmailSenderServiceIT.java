package org.dsacleveland.evictiontracker.service.utils;

import com.sendgrid.SendGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
class EmailSenderServiceIT {

    private EmailSenderService classUnderTest;

    @BeforeEach
    public void setup() {
        String key = System.getProperty("sendgrid-apikey");

        classUnderTest = new EmailSenderService(new SendGrid(key));
    }

    @Test
    void sendMessage() throws IOException {
        classUnderTest.sendMessage("gaovinal@gmail.com", "this is a test");
    }
}