package eu.aragonapp.colorrat.network.packet;

import eu.aragonapp.colorrat.network.NetworkConnection;

import java.io.Serializable;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public abstract class Packet implements Serializable {

    public abstract void execute(NetworkConnection connection);

}
