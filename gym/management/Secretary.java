package gym.management;

import gym.Exception.*;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;
import gym.management.Sessions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a Secretary responsible for managing gym operations, including client registration, hiring instructors,
 * creating sessions, and sending notifications.
 * The Secretary is associated with a specific gym and performs various administrative tasks.
 */
public class Secretary extends Person implements Subject {
    private int salary;
    private boolean active;
    protected Gym gym;

    /**
     * Constructs a new Secretary.
     *
     * @param p the person information for the secretary
     * @param salary the monthly salary of the secretary
     * @param gym the gym the secretary is associated with
     */
    public Secretary(Person p,int salary,Gym gym) {
        super(p.getName(),p.getBalance(),p.getGender(),p.getBirthDate().toString(), p.getId());
        this.salary = salary;
        this.active = true;
        this.gym = gym;
    }

    /**
     * Gets the current balance of the gym.
     *
     * @return the gym's balance
     */
    public int getBalanceGym(){
        return gym.balanceGym;
    }

    /**
     * Gets the salary of the secretary.
     *
     * @return the secretary's salary
     */
    public int getSalary(){
        return salary;
    }

    /**
     * Reduces the gym's balance by a specified amount.
     *
     * @param balance the amount to deduct from the gym's balance
     */
    public void reduceBalanceGym(int balance) {
        gym.reduceBalanceGym(balance);
    }

    /**
     * Adds a specified amount to the gym's balance.
     *
     * @param sum the amount to add to the gym's balance
     */
    public void addToBalanceGym(int sum){
        gym.addToeBalanceGym(sum);
    }

    /**
     * Sets the activity status of the secretary.
     *
     * @param b true to activate the secretary, false to deactivate
     */
    public void setActive(boolean b) {
        this.active=b;
    }

    /**
     * Registers a client in the gym.
     *
     * @param p2 the person to register as a client
     * @return the registered client
     * @throws InvalidAgeException if the person is under 18
     * @throws DuplicateClientException if the person is already registered
     */
    public Client registerClient(Person p2) throws InvalidAgeException,DuplicateClientException{
        if(!active) { // if the secretary is an old one
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");

        }
        return RegisterClientAction.registerClient(p2, this);
    }

    /**
     * Unregister a client from the gym and all sessions they are enrolled in.
     *
     * @param c2 the client to unregister
     * @throws ClientNotRegisteredException if the client is not registered in the gym
     */
    public void unregisterClient(Client c2)throws ClientNotRegisteredException {
        if(!active) { // if the secretary is an old one
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        RegisterClientAction.unregisterClient(c2, this);
    }

    /**
     * Hires an instructor and adds them to the gym.
     *
     * @param p4 the person information of the instructor
     * @param sessionTypes the session types the instructor is qualified to teach
     * @return the hired instructor
     */
    public Instructor hireInstructor(Person p4, int i, ArrayList<SessionType> sessionTypes) {
        if(!active) {// if the secretary is an old one
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        Instructor pI=new Instructor(p4,i,sessionTypes);
        gym.instructors.add(pI);
        gym.actionsHistory.add("Hired new instructor: "+pI.getName()+" with salary per hour: "+ pI.getSalary());
        return pI;
    }

    /**
     * Creates and adds a session to the gym.
     *
     * @param sessionType the type of the session
     * @param s the date of the session
     * @param forumType the forum type of the session
     * @param i2 the instructor for the session
     * @return the created session
     * @throws InstructorNotQualifiedException if the instructor is not qualified to teach the session type
     */
    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2)throws InstructorNotQualifiedException {
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        if (!i2.isQualifiedFor(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        Session ss = SessionFactory.createSession(sessionType, i2, s, forumType);
        if (gym.sessions.contains(ss)) {
            for (Session se : gym.sessions) {
                if (s.equals(ss)) {
                    return se;
                }
            }
        }
        gym.sessions.add(ss);
        i2.addSession(ss);
        gym.actionsHistory.add("Created new session: " + sessionType + " on " + ss.getDateTime() + " with instructor: " + i2.getName());
        return ss;
    }

    /**
     * Registers a client to a session.
     *
     * @param c the client to register
     * @param s the session to register the client in
     * @throws DuplicateClientException if the client is already registered for the session
     * @throws ClientNotRegisteredException if the client is not registered in the gym
     */
    public void registerClientToLesson(Client c, Session s) throws NullPointerException ,DuplicateClientException,ClientNotRegisteredException{
        if(!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
         }
        RegisterClientAction.registerClientToLesson(c, s, this);
    }

    /**
     * Pays salaries to the secretary and all instructors based on their sessions.
     */
    public void paySalaries() {
        if(!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        super.getBalance().addBalance(salary);
        reduceBalanceGym(salary);
        for (Instructor instructor : gym.instructors) {
            int sessionCount = instructor.getSessions().size();
            int perSessionSalary = instructor.getSalary();
            int totalSalary = sessionCount * perSessionSalary;
            instructor.getBalance().addBalance(totalSalary);
            reduceBalanceGym(totalSalary);
        }
        gym.actionsHistory.add("Salaries have been paid to all employees");
    }

    /**
     * Prints the action history of the gym.
     */
    public void printActions() {
        if(!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for(String s: gym.actionsHistory){
            System.out.println(s);
        }
    }

    /**
     * Sends a notification to all participants in a session.
     *
     * @param s4 the session for which participants will be notified
     * @param s the notification message
     */
    @Override
    public void notify(Session s4, String s) throws NullPointerException {
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for(Client c : s4.getParticipants()){
            c.update(s);
        }
        gym.actionsHistory.add("A message was sent to everyone registered for session " + s4.getType() + " on " + s4.getDateTime().toString() + " : " + s);
    }

    /**
     * Sends a notification to all participants registered for sessions on a specific date.
     *
     * @param date the date of the sessions in the format dd-MM-yyyy
     * @param message the notification message
     */
    @Override
    public void notify(String date, String message) throws NullPointerException{
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate targetDate = LocalDate.parse(date, inputFormatter);
        for (Session session : gym.sessions) {
            // Convert session's datetime to LocalDate (ignoring time part)
            LocalDate sessionDate = session.getDateTime().toLocalDate();
            if (sessionDate.equals(targetDate)) {  // Compare only the date part
                for(Client c : session.getParticipants()){
                    if(!c.getNotifications().contains(message)) {
                        c.update(message);
                    }
                }
            }
        }
        gym.actionsHistory.add("A message was sent to everyone registered for a session on "+ targetDate +" : "+message);
    }

    /**
     * Sends a notification to all clients in the gym.
     *
     * @param s the notification message
     */
    @Override
    public void notify(String s) {
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for (Client c : gym.clients) {
            c.update(s);
        }
        gym.actionsHistory.add("A message was sent to all gym clients: " + s);
    }

    /**
     * Returns a string representation of the Secretary.
     *
     * @return a string containing details about the secretary
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "ID: " + super.getId() +
                " | Name: " + super.getName() +
                " | Gender: " + super.getGender().toString() +
                " | Birthday: " + super.getBirthDate().format(formatter) +
                " | Age: " + super.getAge() +
                " | Balance: " + super.getBalanceInt() +
                " | Role: Secretary" +
                " | Salary per Month: " + salary;
    }

}













