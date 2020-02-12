package domain;

import sun.security.krb5.internal.Ticket;

import java.io.*;
import java.util.ArrayList;

public class Order
{
    private int orderNr;
    private boolean isStudentOrder;

    private ArrayList<MovieTicket> tickets;

    public Order(int orderNr, boolean isStudentOrder)
    {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;

        tickets = new ArrayList<MovieTicket>();
    }

    public int getOrderNr()
    {
        return orderNr;
    }

    public void addSeatReservation(MovieTicket ticket)
    {
        tickets.add(ticket);
    }

    public double calculatePrice()
    {
        double price = 0;
        boolean isWeekday;

        switch (tickets.get(0).getDate().getDayOfWeek()) {
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:
                isWeekday = false;
                break;
            default:
                isWeekday = true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (!this.isStudentOrder && isWeekday) {
                price = getPrice(price, i);
            } else if (this.isStudentOrder) {
                price = getPrice(price, i);
            } else {
                price = getPremiumPrice(price, i);
            }
        }

        if (!this.isStudentOrder && !isWeekday && tickets.size() >= 6) {
            price = price / 100 * 90;
        }

        return price;
    }

    private double getPremiumPrice(double price, int i) {
        if (tickets.get(i).isPremiumTicket() && this.isStudentOrder) {
            price = price + tickets.get(i).getPrice() + 2;
        } else if (tickets.get(i).isPremiumTicket() && !this.isStudentOrder) {
            price = price + tickets.get(i).getPrice() + 3;
        } else {
            price = price + tickets.get(i).getPrice();
        }
        return price;
    }

    private double getPrice(double price, int i) {
        if (i % 2 == 1) {
            price = getPremiumPrice(price, i);
        }
        return price;
    }

    public void export(TicketExportFormat exportFormat) throws IOException {

        String enter = System.getProperty("line.separator");
        FileOutputStream fos = new FileOutputStream("Order_" + orderNr);
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

        for (int i = 0; i < tickets.size(); i++) {
            MovieTicket ticket = tickets.get(i);
            String str = ticket.toString() + String.valueOf(ticket.getPrice());
            outStream.writeUTF(str  + enter);
        }

        outStream.close();


        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json
    }
}
