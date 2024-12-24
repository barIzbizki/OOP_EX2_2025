/**
 * The Client class represents a gym client who is a person and an observer.
 * Clients can receive notifications and manage their scheduled sessions.
 */
package gym.customers;

import gym.management.Observer;
import gym.management.Sessions.Session;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements Observer {
    /**
     * List of sessions the client is registered for.
     */
    private List<Session> sessions;

    /**
     * Constructs a Client object based on a Person instance.
     *
     * @param p the Person instance to create a Client from.
     */
    public Client(Person p) {
        super(p.getName(), p.getBalance(), p.getGender(), p.getBirthDate().toString(), p.getId());
        this.sessions = new ArrayList<>();
    }

    /**
     * Retrieves the notifications for the client.
     *
     * @return a list of notification messages received by the client.
     */
    public List<String> getNotifications() {
        return super.getNotifications();
    }

    /**
     * Retrieves the list of sessions the client is registered for.
     *
     * @return a list of sessions.
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Adds a session to the client's list of sessions.
     *
     * @param session the session to add.
     */
    public void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Checks if this client is equal to another object.
     *
     * @param obj the object to compare to.
     * @return true if the object is a Client with the same attributes, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        return super.equals(other);
    }

    /**
     * Updates the client with a new notification message.
     *
     * @param message the notification message to add.
     */
    @Override
    public void update(String message) {
        getNotifications().add(message);
    }

    /**
     * Returns a string representation of the client, including ID, name, gender, birthdate, age, and balance.
     *
     * @return a formatted string representing the client's details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "ID: " + super.getId() +
                " | Name: " + getName() +
                " | Gender: " + super.getGender().toString() +
                " | Birthday: " + super.getBirthDate().format(formatter) +
                " | Age: " + super.getAge() +
                " | Balance: " + super.getBalanceInt();
    }
}
