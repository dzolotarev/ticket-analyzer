package ru.dzolotarev;

import lombok.Getter;
import org.apache.commons.lang3.SerializationUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TicketListBuilder {
    private final List<Ticket> ticketList;

    private TicketListBuilder(Builder builder) {
        this.ticketList = builder.ticketList;
    }

    public static class Builder {
        private List<Ticket> ticketList;

        public Builder(List<Ticket> ticketList) {
            this.ticketList = ticketList.stream().map(SerializationUtils::clone).collect(Collectors.toList());
        }

        public Builder findTicketsByCarrier(String carrier) {
            ticketList = ticketList.stream().filter(item -> carrier.equals(item.getCarrier()))
                    .collect(Collectors.toList());
            return this;
        }

        public Builder findTicketsByOriginDestination(String origin, String destination) {
            ticketList = ticketList.stream().filter(ticket -> ticket.getOrigin().equals(origin))
                    .filter(ticket -> ticket.getDestination().equals(destination))
                    .collect(Collectors.toList());
            return this;
        }

        public Builder findMinFlightTime() {
            Ticket result = ticketList.stream().min(Comparator.comparing(ticket -> Duration.between(LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime()), LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime()))))
                    .orElse(new Ticket());

            long time = ticketList.stream()
                    .mapToLong(ticket -> Duration.between(LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime()), LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime())).toMinutes())
                    .min()
                    .orElse( 0);

            String timeTo = String.format("%d:%02d", time / 60, time % 60);
            System.out.println(timeTo);

            ticketList = List.of(result);
            return this;
        }

        public TicketListBuilder build() {
            return new TicketListBuilder(this);
        }
    }
}
