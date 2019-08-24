package com.goJek.service;

import com.goJek.models.Ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketServiceImpl implements TicketService {

    private static TicketService ticketService;

    public List<Ticket> allTickets = new ArrayList<>();

    public static TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }

        return ticketService;
    }


    @Override
    public Ticket createTicket(String registrationNumber, int slotId, int parkingLotId) {
        Ticket ticket = new Ticket(registrationNumber, slotId, parkingLotId);
        allTickets.add(ticket);
        return ticket;
    }

    @Override
    public void closeTicket(int slotNum, int parkingLotId) {
        Ticket openTicket = getOpenTicket(slotNum, parkingLotId);
        if (openTicket != null) {
            openTicket.closeTicket();
        }
    }

    private Ticket getOpenTicket(int slotNum, int parkingLotId) {
        for (Ticket ticket : allTickets) {
            if (ticket.getSlotId() == slotNum && ticket.getparkingLotId() == parkingLotId && ticket.getEndTimestamp() == -1) {
                return ticket;
            }
        }

        return null;
    }
}
