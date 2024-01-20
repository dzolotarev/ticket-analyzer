package ru.dzolotarev;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public List<Ticket> getMinimalFlightTimeTickets(String origin, String destination) {
        if (isEmpty(origin) || isEmpty(destination)) {
            throw new IllegalArgumentException("Destination and/or origin can not be null or empty!");
        }
        return ticketService.calcMinimalFlightTimeTickets(origin, destination);
    }

    public Double getAvgMedianDeltaPrice(String origin, String destination) {
        if (isEmpty(origin) || isEmpty(destination)) {
            throw new IllegalArgumentException("Destination and/or origin can not be null or empty!");
        }
        return ticketService.calcAvgMedianDeltaPrice(origin, destination);
    }
}
