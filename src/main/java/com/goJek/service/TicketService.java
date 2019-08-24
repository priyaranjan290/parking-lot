package com.goJek.service;

import com.goJek.models.Ticket;


public interface TicketService {

    Ticket createTicket(String registrationNumber, int slotId, int parkingLotId);

    void closeTicket(int slotNum, int parkingLotId);
}
