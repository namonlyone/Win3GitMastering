package com.aavn.demo.service;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;

/**
 * 
 * @author lqdung
 *
 */
public class ClientProvider {
    
    private static ClientProvider instance = null;
    private static Object lock      = new Object();
    
    private Client client;
    private Node node;

    public static ClientProvider instance(){
        
        if(instance == null) { 
            synchronized (lock) {
                if(null == instance){
                    instance = new ClientProvider();
                }
            }
        }
        return instance;
    }

    public void prepareClient(){
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "dungle").build();
    	client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public void closeNode(){
        
        if (!node.isClosed()) {
        	node.close();
        }
    }
    
    public Client getClient(){
        return client;
    }
    
    public void printThis() {
        System.out.println(this);
    }
    
}
