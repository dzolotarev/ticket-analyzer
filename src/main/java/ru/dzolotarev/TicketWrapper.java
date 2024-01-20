package ru.dzolotarev;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;

import java.util.List;

public class TicketWrapper {
    @Getter
    public List<Ticket> ticketList;

    @JsonAnySetter
    public void set(String key, List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
