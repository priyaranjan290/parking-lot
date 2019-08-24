package com.goJek.service;

import com.goJek.models.Slot;
import com.goJek.models.Ticket;


public class TicketServiceImpl implements TicketService {

    private static TicketService ticketService;

    public static TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }

        return ticketService;
    }

    @Override
    public Ticket createTicket(Slot slot, int parkingLotId) {
        return new Ticket(slot, parkingLotId);
    }
}
