package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class provides static methods for managing client registration actions in the gym.
 * It handles registering clients to the gym, enrolling them in sessions, and unregistering them from the gym.
 */
public abstract class RegisterClientAction {

    /**
     * Registers a new client to the gym.
     *
     * @param p2 the person to register as a client
     * @param secretary the secretary managing the gym operations
     * @return the newly registered client
     * @throws InvalidAgeException if the person is under 18 or their birth date is null
     * @throws DuplicateClientException if the client is already registered
     */
    public static Client registerClient(Person p2, Secretary secretary) throws InvalidAgeException, DuplicateClientException {
        if (p2.getBirthDate() == null) {
            throw new InvalidAgeException("Error: Birth date is null!");
        }
        if (p2.getAge() < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        Client c = new Client(p2);
        if (secretary.gym.clients.contains(c)) {
            throw new DuplicateClientException("Error: The client is already registered");
        }
        secretary.gym.clients.add(c);
        secretary.gym.actionsHistory.add("Registered new client: " + c.getName());
        return c;
    }

    /**
     * Registers a client for a gym session.
     *
     * @param c1 the client to register
     * @param s1 the session to enroll the client in
     * @param secretary the secretary managing the gym operations
     * @throws DuplicateClientException if the client is already registered for the session
     * @throws ClientNotRegisteredException if the client is not registered in the gym
     */
    public static void registerClientToLesson(Client c1, Session s1, Secretary secretary) throws DuplicateClientException, ClientNotRegisteredException {
        boolean doneIf = false;

        List<Client> clients = secretary.gym.clients;

        if (s1.getParticipants().contains(c1)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }

        if (!clients.contains(c1)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        if (s1.getParticipants().size() == s1.getMaxParticipants()) {
            secretary.gym.actionsHistory.add("Failed registration: No available spots for session");
            doneIf = true;
        }

        if (!s1.getDateTime().isAfter(LocalDateTime.now())) {
            secretary.gym.actionsHistory.add("Failed registration: Session is not in the future");
            doneIf = true;
        }

        if (s1.getForum().toString().equals("Seniors") && c1.getAge() < 65) {
            secretary.gym.actionsHistory.add("Failed registration: Client doesn't meet the age requirements for this session (" + s1.getForum().toString() + ")");
            doneIf = true;
        }

        if ((s1.getForum().toString().equals("Female") && c1.getGender().toString().equals("Male")) ||
                (s1.getForum().toString().equals("Male") && c1.getGender().toString().equals("Female"))) {
            secretary.gym.actionsHistory.add("Failed registration: Client's gender doesn't match the session's gender requirements");
            doneIf = true;
        }

        if (c1.getBalanceInt() - s1.getPrice() < 0) {
            secretary.gym.actionsHistory.add("Failed registration: Client doesn't have enough balance");
            doneIf = true;
        }

        if (!doneIf) {
            s1.registerClient(c1);
            c1.getBalance().reduceBalance(s1.getPrice());
            secretary.addToBalanceGym(s1.getPrice());
            secretary.gym.actionsHistory.add("Registered client: " + c1.getName() + " to session: " + s1.getType().toString() +
                    " on " + s1.getDateTime() + " for price: " + s1.getPrice());
        }
    }

    /**
     * Unregisters a client from the gym and removes them from all sessions they are enrolled in.
     *
     * @param c2 the client to unregister
     * @param secretary the secretary managing the gym operations
     * @throws ClientNotRegisteredException if the client is not registered in the gym
     */
    public static void unregisterClient(Client c2, Secretary secretary) throws ClientNotRegisteredException {
        if (!secretary.gym.clients.contains(c2)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        secretary.gym.clients.remove(c2);
        for (int i = 0; i < c2.getSessions().size(); i++) {
            Session s2 = c2.getSessions().get(i);
            s2.getParticipants().remove(c2);
        }
        secretary.gym.actionsHistory.add("Unregistered client: " + c2.getName());
    }
}

