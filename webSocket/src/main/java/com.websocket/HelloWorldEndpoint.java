package com.websocket;

/**
 * Created by Administrator on 2017/04/10.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import com.websocket.model.*;

@ServerEndpoint(value = "/hello",
        decoders = {
                MessageDecoder.class,},
        encoders = {
                MessageEncoder.class
        })
public class HelloWorldEndpoint {

    @OnMessage
    public Person hello(Person person, Session session) {
        if (person.getName().equals("john")) {
            person.setName("Mr. John");
        }
        try {
            session.getBasicRemote().sendObject(person);
            System.out.println("sent ");
        } catch (Exception ex) {
            Logger.getLogger(HelloWorldEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;

    }

    @OnOpen
    public void myOnOpen(Session session) {
    }

}

