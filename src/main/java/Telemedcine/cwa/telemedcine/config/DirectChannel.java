/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Telemedcine.cwa.telemedcine.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;


public class DirectChannel implements MessageChannel {

    public DirectChannel() {
    }

    @Override
    public boolean send(@SuppressWarnings("null") Message<?> message, long timeout) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
