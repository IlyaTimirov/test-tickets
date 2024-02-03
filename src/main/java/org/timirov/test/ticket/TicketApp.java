package org.timirov.test.ticket;

import org.timirov.test.ticket.entities.Tickets;
import org.timirov.test.ticket.service.TicketService;

import java.io.File;
import java.util.Map;


public class TicketApp {
    public final static String PATH = "src/main/resources/tickets.json";

    public static void main(String[] args) {
        File file = new File(PATH);
        TicketService ticketService = new TicketService();
        Tickets tickets = ticketService.fileToTickets(file);
        double differencePrice = ticketService.differenceBetweenAverageAndMediumPrice(tickets.getTickets());
        Map<String, Integer> minimums = ticketService.getMinimumFlightTimeForeachAirCarrier(tickets.getTickets());
        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика: ");
        minimums.keySet().forEach(minimum -> System.out.println("Авиаперевозчик " + minimum + ": " + minimums.get(minimum) + " минут"));
        System.out.println("Разницу между средней ценой и медианой для полета между городами Владивосток и Тель-Авив составляет: " + differencePrice);
    }

}
