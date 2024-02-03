package org.timirov.test.ticket.entities;

import lombok.Data;

import java.util.List;

@Data
public class Tickets {
    private List<Ticket> tickets;
}
