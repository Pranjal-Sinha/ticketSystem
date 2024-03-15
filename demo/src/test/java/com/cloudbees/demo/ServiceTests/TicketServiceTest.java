package com.cloudbees.demo.ServiceTests;

import com.cloudbees.demo.Service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testPurchaseTicket() {
        // Mock any dependencies if needed
        // Example: when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        ResponseEntity<String> response = ticketService.purchaseTicket("Pranjal", "Sinha", "pran@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ticket purchased successfully", response.getBody());
    }
}