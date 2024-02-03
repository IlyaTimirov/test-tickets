package org.timirov.test.ticket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.timirov.test.ticket.entities.Ticket;
import org.timirov.test.ticket.entities.Tickets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TicketService {

    public Tickets fileToTickets(File file) {
        try {
            return new ObjectMapper().readValue(file, Tickets.class);
        }catch (FileNotFoundException e){
            throw new RuntimeException("Файл не найден!");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int parserStringTimeToInteger(String time) {
        return Integer.parseInt(time.replace(":", ""));
    }

    public Map<String, Integer> getMinimumFlightTimeForeachAirCarrier(List<Ticket> tickets) {
        Map<String, Integer> result = new HashMap<>();

        tickets.forEach(ticket -> {
            int departure = parserStringTimeToInteger(ticket.getDeparture_time());
            int arrival = parserStringTimeToInteger(ticket.getArrival_time());
            int difference = arrival - departure;
            if (result.containsKey(ticket.getCarrier())) {
                if (result.get(ticket.getCarrier()) > difference) {
                    result.put(ticket.getCarrier(), difference);
                }
            }else {
                result.put(ticket.getCarrier(), difference);
            }
        });
        return result;
    }

    public double differenceBetweenAverageAndMediumPrice(List<Ticket> tickets) {
        double average = tickets.stream().mapToInt(Ticket::getPrice).average().orElseThrow();
        double median;
        int[] prices = tickets.stream().mapToInt(Ticket::getPrice).sorted().toArray();
        Arrays.sort(prices);
        if (prices.length % 2 == 0) {
            median = (prices[(prices.length - 1) / 2] + prices[(prices.length - 1) / 2 + 1]);
            median = median / 2;
        } else {
            median = prices[(prices.length) / 2];
        }
        return average - median;
    }
}
