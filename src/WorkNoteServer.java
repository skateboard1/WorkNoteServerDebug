import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by skateboard on 16-2-15.
 */
public class WorkNoteServer {

    private static final int DEFAULT_PORT=8080;


    public void work()
    {
        try {
            ServerSocket serverSocket=new ServerSocket(DEFAULT_PORT);
            while(true)
            {
                System.out.println("server socket start work");
                Socket socket=serverSocket.accept();
                new WorkThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
