import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    public static String createEntry(String type, double amount) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.now().format(formatter)
                + " | "
                + type
                + " | ₹"
                + amount;
    }
}