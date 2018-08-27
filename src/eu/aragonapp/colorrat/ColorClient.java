package eu.aragonapp.colorrat;

import eu.aragonapp.colorrat.network.packet.Packet;
import eu.aragonapp.colorrat.network.thread.types.ConnectThread;
import eu.aragonapp.colorrat.network.thread.types.ReceiveThread;

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
public class ColorClient {

    private static ColorClient instance;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private ConnectThread connectThread;
    private ReceiveThread receiveThread;

    private boolean connected;
    private Socket socket;

    public ColorClient() {
        instance = this;

        this.connectThread = new ConnectThread();
        this.connectThread.start();
    }

    public boolean write(Packet packet) {
        if (this.outputStream == null) throw new NullPointerException("Output Stream is null!");

        try {
            this.outputStream.writeObject(packet);
            this.outputStream.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void stop() {
        try {
            if (this.outputStream != null) this.outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (this.inputStream != null) this.inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (this.socket != null) this.socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.outputStream = null;
        this.inputStream = null;
        this.socket = null;

        this.setConnected(false);
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setReceiveThread(ReceiveThread receiveThread) {
        this.receiveThread = receiveThread;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ReceiveThread getReceiveThread() {
        return receiveThread;
    }

    public static ColorClient getInstance() {
        return instance;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isConnected() {
        return connected;
    }

    public Socket getSocket() {
        return socket;
    }

}
