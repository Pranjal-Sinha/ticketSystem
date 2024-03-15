package com.cloudbees.demo.Controller;

import com.cloudbees.demo.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to the Train Ticketing System!";
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTicket(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        return ticketService.purchaseTicket(firstName, lastName, email);
    }

    @GetMapping("/receipt/{email}")
    public ResponseEntity<String> viewReceipt(@PathVariable String email) {
        return ticketService.viewReceipt(email);
    }

    @GetMapping("/users/{section}")
    public ResponseEntity<String> viewUsersBySection(@PathVariable String section) {
        return ticketService.viewUsersBySection(section);
    }

    @DeleteMapping("/remove/{email}")
    public ResponseEntity<String> removeUser(@PathVariable String email) {
        return ticketService.removeUser(email);
    }

    @PutMapping("/modify-seat/{email}")
    public ResponseEntity<String> modifySeat(@PathVariable String email, @RequestParam String newSeat) {
        return ticketService.modifySeat(email, newSeat);
    }
}

