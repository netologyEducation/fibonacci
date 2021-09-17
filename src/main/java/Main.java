public class Main {
    public static void main(String[] args) {
        final int PORT = 1234;
        final String HOST = "127.0.0.1";

        Thread server = new Thread(new Server(PORT), "Server");
        Thread client = new Thread(new Client(HOST, PORT), "Client");

        server.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.start();

    }
}
