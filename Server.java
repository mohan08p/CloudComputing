import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    private static Socket socket;

    public static void main(String[] args)
    {
         int count=0;
        try
        {


          int port = 25000;
          ServerSocket serverSocket = new ServerSocket(port);



          while(true)
          {

              socket = serverSocket.accept();



            System.out.println("Server Started and listening to the port 25000");

            //Server is running always. This is done using this while(true) loop

            //Reading the message from the client

          System.out.println("Accepted connection : " + socket);
          // send file
          byte [] mybytearray  = new byte [100];
          InputStream is = socket.getInputStream();
          System.out.println(is.toString());
          FileOutputStream fos = new FileOutputStream("a.txt");
          count++;
          BufferedOutputStream bos = new BufferedOutputStream(fos);
          int bytesRead = is.read(mybytearray,0,mybytearray.length);
          bos.write(mybytearray, 0 , bytesRead);
          bos.flush();
          bos.close();
          //socket.close();
          //System.out.println("mybyte array length:"+mybytearray.length);
          int current = bytesRead;
          System.out.println("Got the "+count+"-file.");




          System.out.println(Arrays.toString(mybytearray));
          System.out.println("File " + " downloaded (" + current + " bytes read)");

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
