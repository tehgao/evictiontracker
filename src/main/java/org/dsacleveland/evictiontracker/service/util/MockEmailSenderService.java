package org.dsacleveland.evictiontracker.service.util;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local-dev")
@Primary
@XSlf4j
public class MockEmailSenderService implements EmailSenderService {
    @Override
    public boolean sendMessage(String email, String message) {
        log.info("Mock sending email to " + email + " with message " + message);
        return true;
    }
}
