package gym.management;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Gym, following the Singleton design pattern to ensure a single instance.
 * The Gym manages clients, instructors, sessions, and a secretary. It also maintains
 * the gym's balance and a history of actions performed.
 */
public class Gym {

    /**
     * The single instance of the Gym.
     */
    private static Gym instance;

    /**
     * The name of the gym.
     */
    protected String name;

    /**
     * The secretary of the gym.
     */
    protected static Secretary secretary;

    /**
     * The list of clients registered in the gym.
     */
    protected List<Client> clients;

    /**
     * The list of instructors employed in the gym.
     */
    protected List<Instructor> instructors;

    /**
     * The list of sessions held at the gym.
     */
    protected List<Session> sessions;

    /**
     * The current balance of the gym.
     */
    protected static int balanceGym = 0;

    /**
     * The history of actions performed in the gym.
     */
    protected List<String> actionsHistory;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private Gym() {
        this.name = "Default Gym Name";
        this.secretary = null;
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.actionsHistory = new ArrayList<>();
    }

    /**
     * Retrieves the single instance of the Gym.
     *
     * @return the Gym instance
     */
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    /**
     * Retrieves the name of the gym.
     *
     * @return the name of the gym
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the gym.
     *
     * @param nName the new name of the gym
     */
    public void setName(String nName) {
        name = nName;
    }

    /**
     * Sets a new secretary for the gym. The previous secretary, if any, is deactivated.
     *
     * @param p1 the person to assign as the secretary
     * @param i  the hourly salary of the new secretary
     */
    public void setSecretary(Person p1, int i) {
        if (secretary != null) {
            secretary.setActive(false);
        }
        secretary = new Secretary(p1, i, this);
        actionsHistory.add("A new secretary has started working at the gym: " + secretary.getName());
    }

    /**
     * Decreases the gym's balance.
     *
     * @param balance the amount to subtract from the balance
     */
    protected void reduceBalanceGym(int balance) {
        balanceGym -= balance;
    }

    /**
     * Increases the gym's balance.
     *
     * @param balance the amount to add to the balance
     */
    protected void addToeBalanceGym(int balance) {
        balanceGym += balance;
    }

    /**
     * Retrieves the secretary of the gym.
     *
     * @return the current secretary
     */
    public Secretary getSecretary() {
        return secretary;
    }

    /**
     * Provides a string representation of the gym, including its name, balance,
     * clients, employees, and sessions.
     *
     * @return a detailed string representation of the gym
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Gym Name: ").append(name).append("\n");

        if (secretary != null) {
            sb.append("Gym Secretary: ").append(secretary).append("\n");
        }

        sb.append("Gym Balance: ").append(secretary.getBalanceGym()).append("\n\n");

        // Clients Data
        sb.append("Clients Data:\n");
        for (Client client : clients) {
            sb.append(client).append("\n");
        }

        sb.append("\n");

        // Employees Data
        sb.append("Employees Data:\n");
        for (Instructor instructor : instructors) {
            sb.append(instructor).append("\n");
        }
        if (secretary != null) {
            sb.append(secretary).append("\n");
        }

        sb.append("\n");

        // Sessions Data
        sb.append("Sessions Data:\n");
        for (Session session : sessions) {
            sb.append(session).append("\n");
        }

        return sb.toString();
    }
}
