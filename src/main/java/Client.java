import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private final int PORT;
    private final String HOST;

    public Client(String HOST, int PORT) {
        this.PORT = PORT;
        this.HOST = HOST;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.printf("Количество чисел Фибоначчи (для выхода введите - end): ");
                msg = scanner.nextLine().trim();
                out.println(msg);
                if ("end".equals(msg)) {
                    break;
                }
                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
