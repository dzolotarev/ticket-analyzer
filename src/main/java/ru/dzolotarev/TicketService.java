package ru.dzolotarev;

import com.google.common.math.Quantiles;
import com.google.common.math.Stats;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> calcMinimalFlightTimeTickets(String origin, String destination) {
        List<Ticket> result = new ArrayList<>();
        ticketRepository.getAllCarriers().forEach(carrier -> {
            TicketListBuilder ticketListBuilder = new TicketListBuilder.Builder(ticketRepository.getAllTickets())
                    .findTicketsByCarrier(carrier)
                    .findTicketsByOriginDestination(origin, destination)
                    .findMinFlightTime()
                    .build();
            result.addAll(ticketListBuilder.getTicketList());
        });

        return result;
    }

    public Double calcAvgMedianDeltaPrice(String origin, String destination) {
        TicketListBuilder ticketListBuilder = new TicketListBuilder.Builder(ticketRepository.getAllTickets())
                .findTicketsByOriginDestination(origin, destination)
                .build();

        List<Double> ticketListPrices = ticketListBuilder.getTicketList().stream()
                .map(Ticket::getPrice)
                .toList();

        return Stats.meanOf(ticketListPrices) - Quantiles.median().compute(ticketListPrices);
    }
}
