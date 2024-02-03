package org.timirov.test.ticket.service;

import org.junit.jupiter.api.Test;
import org.timirov.test.ticket.entities.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    @Test
    public void difference_Average_Medium_Price_Odd(){
        List<Ticket> tickets = List.of(Ticket.builder().price(20).build(),
                Ticket.builder().price(60).build(),
                Ticket.builder().price(10).build());
        TicketService ticketService = new TicketService();
        double difference = ticketService.differenceBetweenAverageAndMediumPrice(tickets);

        assertEquals(10, difference);

    }

    @Test
    public void difference_Average_Medium_Price_Even(){
        List<Ticket> tickets = List.of(Ticket.builder().price(20).build(),
                Ticket.builder().price(40).build(),
                Ticket.builder().price(10).build(),
                Ticket.builder().price(30).build());
        TicketService ticketService = new TicketService();
        double difference = ticketService.differenceBetweenAverageAndMediumPrice(tickets);

        assertEquals(0, difference);

    }
}