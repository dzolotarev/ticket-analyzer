package ru.dzolotarev;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketRepository {
    private List<Ticket> ticketList;
    private final File file;
    private final ObjectMapper objectMapper;

    public TicketRepository(File file) {
        this.file = file;
        objectMapper = new ObjectMapper();
        ticketList = new ArrayList<>();
    }

    void init() throws IOException {
        ticketList = objectMapper.readValue(file, TicketWrapper.class).getTicketList();
    }

    public List<Ticket> getAllTickets() {
        return Collections.unmodifiableList(ticketList);
    }

    public Set<String> getAllCarriers() {
        return ticketList.stream().map(Ticket::getCarrier).collect(Collectors.toSet());
    }
}
