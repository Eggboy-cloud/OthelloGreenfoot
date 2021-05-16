import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;

public class OutPrintWriter extends OthelloServer {

    public OutPrintWriter(PrintWriter out, String id){
        this.out = out;
        this.id = id;
    }
    public PrintWriter out;
    public String id;
}