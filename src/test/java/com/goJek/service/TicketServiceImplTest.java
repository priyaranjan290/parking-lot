package com.goJek.service;

import com.goJek.models.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TicketServiceImplTest {

    TicketService ticketService = TicketServiceImpl.getInstance();

    @Test
    public void createTicket() {
        String registrationNumber = "REG_1";
        int slotId = 1;
        int parkingLotId = 1;
        Ticket result = ticketService.createTicket(registrationNumber, slotId, parkingLotId);
        Assert.assertEquals("End timestamp is not same", -1, result.getEndTimestamp());
        Assert.assertEquals("Parking lot id is not same", 1, result.getparkingLotId());
        Assert.assertEquals("Slot id is not same", 1, result.getSlotId());
    }

    private void compareTickets(Ticket result, Ticket expected) {
        Assert.assertEquals("End timestamp is not same", expected.getEndTimestamp(), result.getEndTimestamp());
        Assert.assertEquals("Parking lot id is not same", expected.getparkingLotId(), result.getparkingLotId());
        Assert.assertEquals("Slot id is not same", expected.getSlotId(), result.getSlotId());
    }


    @Test
    public void getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        list.add(ticketService.createTicket("REG_1",1,1));
        list.add(ticketService.createTicket("REG_2",2,1));

        Map<Integer, Ticket> ticketMapCreated = list.stream().collect(Collectors.toMap(ticket -> ticket.getId(), ticket -> ticket));
        Map<Integer, Ticket> ticketMapFetched = ticketService.getAllTickets().stream().collect(Collectors.toMap(ticket -> ticket.getId(), ticket -> ticket));


        for (Map.Entry<Integer, Ticket> entry : ticketMapCreated.entrySet()) {
            if (ticketMapFetched.containsKey(entry.getKey())) {
                compareTickets(ticketMapFetched.get(entry.getKey()), entry.getValue());
            } else {
                Assert.assertTrue("Ticket id not found!", false);
            }
        }

    }


    @Test
    public void closeTicket() {

        List<Ticket> allTickets = ticketService.getAllTickets();

        if (allTickets == null || allTickets.size() == 0) {
            ticketService.createTicket("REG_1",1,1);
            ticketService.createTicket("REG_2",2,1);
            ticketService.createTicket("REG_3",3,1);
        }

        ticketService.closeTicket(2,1);

        List<Ticket> allTicketsMatched = ticketService.getAllTickets().stream().filter(x -> x.getSlotId() == 2 && x.getparkingLotId() == 1).collect(Collectors.toList());

        if (allTicketsMatched.size() == 1) {
            Ticket fetched = allTicketsMatched.get(0);
            Assert.assertNotEquals("Ticket not closed!", -1, fetched.getEndTimestamp());
        } else {

            Assert.assertTrue("Ticket data malformed!", false);
        }

    }

}