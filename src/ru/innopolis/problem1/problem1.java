import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Problem1 {
    public static void main(String[] args) {
    }
}

interface Handler{
    void setNext(Handler handler);
    void handle(String request, List<String> logs);
}

class FileWriter implements Handler{
    PrintWriter writer;
    private Handler next;
    public FileWriter(String fileName, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(fileName, encoding);
    }
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(String request, List<String> logs) {
        if(request.equals("File")){
            for (String log:
                 logs) {
                writer.println(log);
            }
        }
        else {
            next.handle(request, logs);
        }
    }
}

class ConsoleWriter implements Handler{
    private Handler next;
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(String request, List<String> logs) {
        if(request.equals("Console")){
            for (String log:
                    logs) {
                System.out.println(log);
            }
        }
        else {
            next.handle(request, logs);
        }
    }
}

class ServerSender implements Handler{
    private Handler next;
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(String request, List<String> logs) {
        if(request.equals("Server")){
            for (String log:
                    logs) {
                //send to server
            }
        }
        else {
            if(next != null){
                next.handle(request, logs);
            }
        }
    }
}
