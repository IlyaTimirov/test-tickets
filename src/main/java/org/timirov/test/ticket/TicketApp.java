package org.timirov.test.ticket;

import org.timirov.test.ticket.entities.Tickets;
import org.timirov.test.ticket.service.TicketService;
import org.timirov.test.ticket.utils.ParserUtils;

import java.util.Map;


public class TicketApp {

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        Tickets tickets = ticketService.getTickets();
        double differencePrice = ticketService.differenceBetweenAverageAndMediumPrice(tickets.getTickets());
        Map<String, Long> carriers = ticketService.getMinimumFlightTimeForeachCarrier(tickets.getTickets());
        print(carriers, differencePrice);
    }

    private static void print(Map<String, Long> carriers, double differencePrice){
        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика: ");
        carriers.keySet().forEach(minimum -> System.out.println("Авиаперевозчик " + minimum + ": " + carriers.get(minimum) + " минут"));
        System.out.println("Разницу между средней ценой и медианой для полета между городами Владивосток и Тель-Авив составляет: " + differencePrice);
    }
}
