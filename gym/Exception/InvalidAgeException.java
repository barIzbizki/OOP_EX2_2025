package gym.Exception;

public class InvalidAgeException extends Exception {
  // קונסטרוקטור
  public InvalidAgeException(String message) {
    super(message);  // מעביר את ההודעה לאבא (Exception)
  }
}

