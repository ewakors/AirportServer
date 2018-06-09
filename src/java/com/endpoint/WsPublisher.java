package com.endpoint;

import javax.xml.ws.Endpoint;
import com.ws.AirportService;
import java.io.IOException;
import java.net.URISyntaxException;

//Endpoint publisher
public class WsPublisher{
 
	public static void main(String[] args) throws IOException, URISyntaxException {
	   Endpoint.publish("http://localhost:8888/ws/server", new AirportService());
	   
	   System.out.println("Service is published!");                                          
    }
}