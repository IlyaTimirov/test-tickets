package org.timirov.test.ticket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.timirov.test.ticket.TicketApp;
import org.timirov.test.ticket.entities.Ticket;
import org.timirov.test.ticket.entities.Tickets;
import org.timirov.test.ticket.utils.ParserUtils;

import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class TicketService {
    private final static String ORIGIN_VVO = "VVO";
    private final static String DESTINATION_TLV = "TLV";

    private final ParserUtils parser = new ParserUtils();

    private long flightTime(Date dateDeparture, Date dateArrival){
        return Duration.between(dateDeparture.toInstant(), dateArrival.toInstant()).toMinutes();
    }

    public Map<String, Long> getMinimumFlightTimeForeachCarrier(List<Ticket> tickets) {
        Map<String, Long> result = new HashMap<>();
        filterOriginAndDestination(tickets).forEach(ticket -> {
            Date dateDeparture = parser.parserStringToDate(ticket.getDeparture_date() + " " + ticket.getDeparture_time());
            Date dateArrival = parser.parserStringToDate(ticket.getArrival_date() + " " + ticket.getArrival_time());
            long flightTime = flightTime(dateDeparture, dateArrival);
            if (result.containsKey(ticket.getCarrier())) {
                if (result.get(ticket.getCarrier()) > flightTime) {
                    result.put(ticket.getCarrier(), flightTime);
                }
            } else {
                result.put(ticket.getCarrier(), flightTime);
            }
        });
        return result;
    }

    public double differenceBetweenAverageAndMediumPrice(List<Ticket> tickets) {
        double average = filterOriginAndDestination(tickets).stream()
                .mapToInt(Ticket::getPrice)
                .average()
                .orElseThrow();
        int[] prices = filterOriginAndDestination(tickets).stream()
                .mapToInt(Ticket::getPrice)
                .sorted()
                .toArray();
        double median;
        int index = prices.length / 2;
        if (prices.length % 2 == 0) {
            double tmp = prices[index - 1] + prices[index];
            median = tmp / 2;
        } else {
            median = prices[index];
        }
        return average - median;
    }

    private List<Ticket> filterOriginAndDestination(List<Ticket> tickets) {
        return tickets.stream()
                .filter(ticket1 -> ticket1.getOrigin().equals(ORIGIN_VVO) && ticket1.getDestination().equals(DESTINATION_TLV))
                .toList();
    }
}
