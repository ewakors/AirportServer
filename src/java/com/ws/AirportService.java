package com.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import com.data.Flight;
import com.data.Reservation;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.jws.HandlerChain;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@MTOM
@WebService
//@HandlerChain(file = "handler-chain.xml")
public class AirportService {

    private List<Reservation> reservList1;

    @WebMethod
    public List<Flight> getFlightList() {

        List<Flight> list = new ArrayList<>();

//        // JDBC driver name and database URL 
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/mydb";
//
//        // Database credentials 
        String USER = "root";
        String PASS = "newpassword";
//
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String cityFrom = null;
        System.out.println("Database Connecting...");
        try {
//
            System.out.println("Connecting to a selected database...");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = (Statement) conn.createStatement();
            String sql = "SELECT * FROM flight ";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                cityFrom = rs.getString("cityFrom");
                String cityTo = rs.getString("cityTo");
                String day = rs.getString("day");
                String time = rs.getString("time");

                // Setting the values
                Flight flight = new Flight(id, cityFrom, cityTo, day, time);

                flight.setID(id);
                flight.setCityFrom(cityFrom);
                flight.setCityTo(cityTo);
                flight.setDay(day);
                flight.setTime(time);

                list.add(flight);
                System.out.println("ID: " + id);
                System.out.println("name: " + cityFrom);
                list.add(flight);
                
                //product1.add(new Product("2","product2","category2","4.80 zł"));
            }
        } catch (Exception e) {
            System.out.println("Database error..." + e);
        }

        return list;
    }

    @WebMethod
    public List<Reservation> getReservationList(int idFlight) {

        Reservation reserv;

        if (reservList1 == null) {
            reservList1 = new ArrayList<>();
            int id = 0;
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 26; j++) {
                    reserv = new Reservation(id, i, 0);
                    reservList1.add(reserv);
                    id++;
                }
            }
        }

        List<Reservation> reservList2 = new ArrayList<>();

        for (Reservation res : reservList1) {
            if (res.getIdFlight() == idFlight) {
                if (res.getUser() == 0) {
                    reservList2.add(res);
                }
            }
        }

        return reservList2;
    }

    @WebMethod
    public boolean checkReservation(List<Reservation> rezerwacje, int userId) {

        boolean check = true;

        for (Reservation res : rezerwacje) {

            for (Reservation res2 : reservList1) {
                if (res.getIdReservation() == res2.getIdReservation()) {
                    if (res2.getUser() > 0) {
                        check = false;
                    }
                }
            }
        }

        if (check == true) {

            for (Reservation res : rezerwacje) {

                for (Reservation res2 : reservList1) {
                    if (res.getIdReservation() == res2.getIdReservation()) {
                        res2.setUser(userId);

                    }
                }
            }
        }

        return check;
    }

    @WebMethod
    public String getHelloWorldAsString(String name) {

        return "Witaj świecie JAX-WS: " + name;
    }

    @WebMethod
    public void deleteReservation(int idRezerwacji) {

        for (Reservation res : reservList1) {
            if (res.getIdReservation() == idRezerwacji) {

                res.setUser(0);
            }
        }
    }

    @WebMethod
    public List<Reservation> getReservationList2(int idUser) {

        Reservation reserv;

        if (reservList1 == null) {
            reservList1 = new ArrayList<>();
            int id = 0;
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 26; j++) {
                    reserv = new Reservation(id, i, 0);
                    reservList1.add(reserv);
                    id++;
                }
            }
        }

        List<Reservation> reservList2 = new ArrayList<>();

        for (Reservation res : reservList1) {
            if (res.getUser() == idUser) {

                reservList2.add(res);
            }
        }

        return reservList2;
    }

}
