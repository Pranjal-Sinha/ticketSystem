package com.cloudbees.demo.ControllerTests;

import com.cloudbees.demo.Controller.TicketController;
import com.cloudbees.demo.Service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void testPurchaseTicket() throws Exception {
        when(ticketService.purchaseTicket("Pranjal", "Sinha", "pran@example.com")).thenReturn(ResponseEntity.ok("Ticket purchased successfully"));

        mockMvc.perform(post("/purchase")
                        .param("firstName", "Pranjal")
                        .param("lastName", "Sinha")
                        .param("email", "pran@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket purchased successfully"));
    }
}