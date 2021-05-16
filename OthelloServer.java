import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;


public class OthelloServer {

    private static final int PORT = 9001;

    /**
     * The set of all names of clients in the chat room. Maintained so that we can check
     * that new clients are not registering name already in use.
     */
    private static Set<String> names = new HashSet<>();

    /**
     * The set of all the print writers for all the clients, used for broadcast.
     */
    private static Set<OutPrintWriter> writers = new HashSet<>();

    /**
     * Application that listens on a port and spawns handler threads.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            int no=0;
            while (true) {
                Socket socket=listener.accept();
                if(no < 2){
                    no++;                    
                    System.out.println("Ny klient: " + no);
                    new Handler(no, socket).start();
                }else{
                    System.out.println("already two clients"); 
                    socket.close();
                }
            }
        }
    }

    /**
     * A handler thread class. Handlers are spawned from the listening loop and are
     * responsible for a dealing with a single client and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private int no;
        private BufferedReader in;
        private OutPrintWriter out;

        /**
         * Constructs a handler thread, squirreling away the socket. All the interesting
         * work is done in the run method.
         */
        public Handler(int no, Socket socket) {
            this.socket = socket;
            this.no = no;
        }

        /**
         * Services this thread's client by repeatedly requesting a screen name until a
         * unique one has been submitted, then acknowledges the name and registers the
         * output stream for the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new OutPrintWriter(new PrintWriter(socket.getOutputStream(), true), UUID.randomUUID().toString());

                System.out.println("Joining");
                writers.add(out);

                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                boolean started=false;
                while (true) {
                    if(started==false){
                        out.out.println(no);
                    }
                    started = true;
                    String input = in.readLine();
                    System.out.println("Input received");
                    if (input == null) {
                        return;
                    }
                    System.out.println(input);
                    for (OutPrintWriter writer : writers) {
                        if (writer.id != out.id) {
                            writer.out.println(input);
                        }
                    }
                }
            }
            catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down! Remove its name and its print writer from
                //  the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Server is closing");
            }

        }
    }
}