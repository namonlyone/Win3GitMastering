package com.aavn.demo.service;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author lqdung
 */
public class ClientProvider {

    private static ClientProvider instance = null;
    private static Object lock = new Object();
    private Client client;

    public static ClientProvider instance() {
        if (instance == null) {
            synchronized (lock) {
                if (null == instance) {
                    instance = new ClientProvider();
                }
            }
        }
        return instance;
    }

    public void prepareClient() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "dungle").build();
        client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.79.33", 9300));


    }

    public Client getClient() {
        return client;
    }
}
