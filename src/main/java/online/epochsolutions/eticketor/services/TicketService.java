package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Section;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

//Method to create Tickets
    public void createTickets(Long slots, Instant startTime, Instant endTime, Double price, String name, String location){
        Ticket ticket = new Ticket();
        ArrayList<Ticket> ticketList = new ArrayList<>();


        for(int i = 0;i<slots;i++){
            ticket.setName(name);
            ticket.setPrice(price);
            ticket.setStartTime(startTime);
            ticket.setEndTime(endTime);
            ticket.setLocation(location);
            ticket.setSection(Section.VIP);
            ticketList.add(ticket);
        }
        ticketRepository.saveAll(ticketList);
    }
}
