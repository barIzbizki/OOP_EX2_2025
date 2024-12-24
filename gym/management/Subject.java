package gym.management;

import gym.management.Sessions.Session;

public interface Subject {
    void notify(String s);
    void notify(String date, String message);
    void notify(Session s4, String s);


}
