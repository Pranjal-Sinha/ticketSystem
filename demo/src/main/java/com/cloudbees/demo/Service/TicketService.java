package com.cloudbees.demo.Service;

import com.cloudbees.demo.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {

    private static Map<String, User> users = new HashMap<>();
    private static Map<String, String> sectionA = new HashMap<>();
    private static Map<String, String> sectionB = new HashMap<>();

    public ResponseEntity<String> purchaseTicket(String firstName, String lastName, String email) {
        User user = new User(firstName,lastName,email);
        users.put(email, user);
        assignSeat(user);
        return ResponseEntity.ok("Ticket purchased successfully.");
    }

    public ResponseEntity<String> viewReceipt(String email) {
        if (users.containsKey(email)) {
            return ResponseEntity.ok(users.get(email).toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    public ResponseEntity<String> viewUsersBySection(String section) {
        if (section.equals("A") || section.equals("B")) {
            return ResponseEntity.ok(getUsersBySection(section));
        } else {
            return ResponseEntity.badRequest().body("Invalid section.");
        }
    }

    public ResponseEntity<String> removeUser(String email) {
        if (users.containsKey(email)) {
            User user = users.get(email);
            if (user.getSection().equals("A")) {
                sectionA.remove(user.getSeat());
            } else {
                sectionB.remove(user.getSeat());
            }
            users.remove(email);
            return ResponseEntity.ok("User removed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    public ResponseEntity<String> modifySeat(String email, String newSeat) {
        if (users.containsKey(email)) {
            User user = users.get(email);
            String oldSeat = user.getSeat();
            if (user.getSection().equals("A")) {
                sectionA.remove(oldSeat);
            } else {
                sectionB.remove(oldSeat);
            }
            user.setSeat(newSeat);
            if (user.getSection().equals("A")) {
                sectionA.put(newSeat, email);
            } else {
                sectionB.put(newSeat, email);
            }
            return ResponseEntity.ok("Seat modified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    private void assignSeat(User user) {
        if (sectionA.size() <= sectionB.size()) {
            user.setSection("A");
            user.setSeat("A" + (sectionA.size() + 1));
            sectionA.put(user.getSeat(), user.getEmail());
        } else {
            user.setSection("B");
            user.setSeat("B" + (sectionB.size() + 1));
            sectionB.put(user.getSeat(), user.getEmail());
        }
    }

    private String getUsersBySection(String section) {
        Map<String, String> selectedSection = section.equals("A") ? sectionA : sectionB;
        StringBuilder usersList = new StringBuilder();
        for (Map.Entry<String, String> entry : selectedSection.entrySet()) {
            usersList.append(entry.getValue()).append(" - Seat: ").append(entry.getKey()).append("\n");
        }
        return usersList.toString();
    }
}