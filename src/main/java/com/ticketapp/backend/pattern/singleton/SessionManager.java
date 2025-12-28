package com.ticketapp.backend.pattern.singleton;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    // Eager initialization (Spring Bean oldugu icin context ayaga kalkarken olusur
    // ve Singleton'dir)
    private final ConcurrentHashMap<String, String> activeUsers = new ConcurrentHashMap<>();

    public void addSession(String token, String email) {
        activeUsers.put(token, email);
    }

    public String getUser(String token) {
        return activeUsers.get(token);
    }

    public void removeSession(String token) {
        activeUsers.remove(token);
    }
}
