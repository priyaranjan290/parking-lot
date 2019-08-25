package com.goJek.service;

import com.goJek.models.Ticket;

/**
 *
 * Interface to hold ticketing related methods, issued by the parking lot manager
 *
 */
public interface TicketService {

    Ticket createTicket(String registrationNumber, int slotId, int parkingLotId);

    void closeTicket(int slotId, int parkingLotId);
}
