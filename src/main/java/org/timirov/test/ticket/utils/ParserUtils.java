package org.timirov.test.ticket.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.timirov.test.ticket.TicketApp;
import org.timirov.test.ticket.entities.Tickets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserUtils {
    public Tickets parserJsonFileToTickets(String path) {
        try (InputStream in = TicketApp.class.getResourceAsStream(path);) {
            return new ObjectMapper().readValue(in, Tickets.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Date parserStringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyHH:mm");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
