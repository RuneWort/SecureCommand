import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InsecureCommandExecutor {

    // Regex per indirizzi IPv4
    private static final String ipv4Regex = "^[0-9.]+$";
    // Regex per indirizzi IPv6
    private static final String ipv6Regex = "^[0-9a-fA-F:]+$";
    // Regex per indirizzi MAC
    private static final String macRegex = "^[0-9a-fA-F:-]+$";
    
    public static void executeCommand(String userInput) {

        try {
            if (!userInput.matches(ipv4Regex)) {
                System.out.println ("Stai gestendo input ipv4");
                String[] command = { "ping", "-c", "4 ", userInput};
            } else if (!userInput.matches(ipv6Regex)) {
                System.out.println ("Stai gestendo input ipv6");
                String[] command = { "ping", "-c", "4 ", userInput};
            } else if (!userInput.matches(macRegex)) {
                System.out.println ("Stai gestendo input macip");
                String[] command = { "ping", "-c", "4 ", userInput};
            } else {
                throw new IllegalArgumentException("Input non valido: inserisci un indirizzo IPv4, IPv6 o MAC valido.");
            }
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            executeCommand(args[0]);
        } else {
            System.out.println("Usage: java InsecureCommandExecutor <IP>");
        }
    }
}
