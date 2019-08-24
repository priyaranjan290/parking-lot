package com.goJek.service;

import com.goJek.models.Slot;
import com.goJek.models.Ticket;


public interface TicketService {

    Ticket createTicket(Slot slot, int parkingLotId);
}
