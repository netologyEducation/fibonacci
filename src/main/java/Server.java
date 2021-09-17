import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int PORT;

    public Server(int PORT) {
        this.PORT = PORT;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            System.out.printf("Жду подключения клиента к %s...\n", serverSocket.getLocalSocketAddress());
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.printf("Клиент - %s присоединился\n", socket.getRemoteSocketAddress());
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    } else {
                        try {
                            Integer.parseInt(line);
                            out.println(new Fibonacci().getFibonacciNumber(Integer.parseInt(line)));
                        } catch (NumberFormatException e) {
                            out.println(line + " - не является числом");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            break;
        }
    }
}
