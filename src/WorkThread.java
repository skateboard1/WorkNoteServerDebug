import java.io.*;
import java.net.Socket;

/**
 * Created by skateboard on 16-2-15.
 */
public class WorkThread extends Thread {

    private Socket mSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    public static final String GET_MENU="get_menu";
    public static final String DATA_NOT_FOUND="data_not_found";

    public WorkThread(Socket socket)
    {
        this.mSocket=socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            reader=new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            String title=reader.readLine();
            if(GET_MENU.equals(title))
            {
                reader=new BufferedReader(new InputStreamReader(new FileInputStream("/home/skateboard/WorkNoteData/WorkNote_Menu.json")));
                String content=reader.readLine();
                StringBuilder result=new StringBuilder();
                while(content!=null)
                {
                    result.append(content).append("\n");
                    content=reader.readLine();
                }
                writer=new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
                if(result!=null) {
                    writer.write(result.toString());
                    writer.flush();
                    writer.close();
                }
                else
                {
                    writer.write(DATA_NOT_FOUND);
                    writer.flush();
                    writer.close();
                }
                reader.close();
                writer.close();
                mSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
