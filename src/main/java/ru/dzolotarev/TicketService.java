package ru.dzolotarev;

import com.google.common.math.Quantiles;

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

        ////Такой вариант дает погрешность окргления
        // Stats.meanOf(ticketListPrices);

        return ticketListPrices.stream()
                .mapToDouble(a -> a)
                .average().orElse(0) - Quantiles.median().compute(ticketListPrices);
    }
}
