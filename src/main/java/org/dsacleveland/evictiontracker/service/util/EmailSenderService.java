package org.dsacleveland.evictiontracker.service.util;

public interface EmailSenderService {
    boolean sendMessage(String email, String message);
}
