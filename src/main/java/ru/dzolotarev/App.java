package ru.dzolotarev;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * Ticket Analyzer Simple App
 */
public class App {
    public static void main(String[] args) throws IOException {
        if (isNull(args[0])) {
            throw new IllegalArgumentException("File can not be empty!");
        }

        TicketRepository ticketRepository = new TicketRepository(new File(args[0]));
        ticketRepository.init();

        TicketController ticketController = new TicketController(new TicketService(ticketRepository));

        Utils.print("=======================================================================");
        Utils.print("Список билетов с минимальным временем полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:");
        ticketController.getMinimalFlightTimeTickets("VVO", "TLV").forEach(Utils::print);
        Utils.print("=======================================================================");
        Utils.print("Разница между средней ценой и медианой для полета между городами  Владивосток и Тель-Авив:");
        Utils.print(ticketController.getAvgMedianDeltaPrice("VVO", "TLV"));
        Utils.print("=======================================================================");
    }
}
