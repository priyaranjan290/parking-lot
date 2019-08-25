package com.goJek.service;

import com.goJek.models.Ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketServiceImpl implements TicketService {

    private static TicketService ticketService;

    private TicketServiceImpl() {}

    public static TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketServiceImpl();
        }

        return ticketService;
    }

    /**
     * Holds the entire list of tickets created.
     */
    public List<Ticket> allTickets = new ArrayList<>();


    /**
     *
     * Issues a new ticket when a new vehicle is parked to a specific location [slot and parking lot]
     *
     * @param registrationNumber    : registration  number of the vehicle
     * @param slotId                : id of the slot
     * @param parkingLotId          : id of the parking lot
     * @return                      : Ticket
     */
    @Override
    public Ticket createTicket(String registrationNumber, int slotId, int parkingLotId) {
        Ticket ticket = new Ticket(registrationNumber, slotId, parkingLotId);
        allTickets.add(ticket);
        return ticket;
    }

    /**
     *
     * closes the ticket issued for the given location
     *
     * @param slotId       :  id of the slot
     * @param parkingLotId :  id of the parking lot
     */
    @Override
    public void closeTicket(int slotId, int parkingLotId) {
        Ticket openTicket = getOpenTicket(slotId, parkingLotId);
        if (openTicket != null) {
            openTicket.closeTicket();
        }
    }

    /**
     *
     * Search an "open ticket" for the given slot id and parking lot id
     *
     * @param slotId       :  id of the slot
     * @param parkingLotId :  id of the parking lot
     * @return             :  Ticket if found; else null
     */
    private Ticket getOpenTicket(int slotId, int parkingLotId) {
        for (Ticket ticket : allTickets) {
            if (ticket.getSlotId() == slotId && ticket.getparkingLotId() == parkingLotId && ticket.getEndTimestamp() == -1) {
                return ticket;
            }
        }

        return null;
    }
}
