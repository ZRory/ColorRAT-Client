package eu.aragonapp.colorrat.network.thread;

/**
 * @Copyright (c) 2018 Mythic Inc. (http://www.mythic.com/) All Rights Reserved.
 * <p>
 * Mythic Inc. licenses this file to you under the Apache License,
 * @Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * @https://www.apache.org/licenses/LICENSE-2.0
 */
public abstract class ColorThread extends Thread {

    public ColorThread(String name) {
        super(name);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.close()));
    }

    @Override
    public void run() {
        while(!this.isInterrupted())
            this.update();
    }

    public abstract void update();

    public void close() {
        this.interrupt();
    }

}
