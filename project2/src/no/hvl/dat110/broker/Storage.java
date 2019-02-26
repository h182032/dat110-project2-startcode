package no.hvl.dat110.broker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

    protected ConcurrentHashMap<String, Set<String>> subscriptions;
    protected ConcurrentHashMap<String, ClientSession> clients;
    protected ConcurrentHashMap<String, Queue<Message>> offlineClients;

    public Storage() {
        subscriptions = new ConcurrentHashMap<String, Set<String>>();
        clients = new ConcurrentHashMap<String, ClientSession>();
        offlineClients = new ConcurrentHashMap<String, Queue<Message>>();
    }

    public Collection<ClientSession> getSessions() {
        return clients.values();
    }

    public Set<String> getTopics() {

        return subscriptions.keySet();

    }

    public ClientSession getSession(String user) {

        ClientSession session = clients.get(user);

        return session;
    }

    public Queue<Message> getOfflineUserMessages(String user) {
        return offlineClients.getOrDefault(user, null);
    }

    public Collection<String> getOfflineUsers() {
        return offlineClients.keySet();
    }

    public Set<String> getSubscribers(String topic) {
        return (subscriptions.get(topic));
    }

    public void addClientSession(String user, Connection connection) {

        // TODO: add corresponding client session to the storage

        ClientSession clientSession = new ClientSession(user, connection);

        //Create a session and add it to clients
        clients.put(user, clientSession);

        //Remove them from offline users
        removeOfflineUser(user);

        // throw new RuntimeException("not yet implemented");

    }

    public void removeClientSession(String user) {

        // TODO: remove client session for user from the storage

        //Remove client from online
        clients.remove(user);

        //Add client to offline clients and create a queue for messages
        addOfflineUser(user);

        // throw new RuntimeException("not yet implemented");

    }

    public void createTopic(String topic) {

        // TODO: create topic in the storage

        Set<String> subscriberSet = new HashSet<String>();

        subscriptions.put(topic, subscriberSet);

        // throw new RuntimeException("not yet implemented");

    }

    public void deleteTopic(String topic) {

        // TODO: delete topic from the storage

        subscriptions.remove(topic);

        //throw new RuntimeException("not yet implemented");

    }

    public void addSubscriber(String user, String topic) {

        // TODO: add the user as subscriber to the topic

        Set<String> subscriberSet = getSubscribers(topic);

        subscriberSet.add(user);

        // throw new RuntimeException("not yet implemented");

    }

    public void removeSubscriber(String user, String topic) {

        // TODO: remove the user as subscriber to the topic

        Set<String> subscriberSet = getSubscribers(topic);

        subscriberSet.remove(user);

        //throw new RuntimeException("not yet implemented");
    }

    public void addOfflineUser(String user) {
        offlineClients.put(user, new LinkedList<>());
    }

    public void removeOfflineUser(String user) {
        offlineClients.remove(user);
    }

    public void sendOfflineMessages(ClientSession session, Queue<Message> messages) {
        if (messages.size() == 0) return;

        while (!messages.isEmpty()) {
            session.send(messages.poll());
        }
    }


}
