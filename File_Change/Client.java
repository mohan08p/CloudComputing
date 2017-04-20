import java.io.*;
import java.util.Arrays;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.FileSystems;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Client
{

    private static Socket socket;

    public static void main(String args[])
    {
        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
            System.out.println(socket);

            //Send the message to the server
            // receive file


            // code for detecting the changes in the directoty starts heretry {
            try{
                WatchService watcher = FileSystems.getDefault().newWatchService();
                Path dir = Paths.get("Test");
                dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

                System.out.println("Watch Service registered for dir: " + dir.getFileName());

                while (true) {
                    WatchKey key;
                    try {
                        key = watcher.take();
                    } catch (InterruptedException ex) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path fileName = ev.context();

                        System.out.println(kind.name() + ": " + fileName);

                        if (kind == ENTRY_CREATE && fileName.toString().equals("a.txt") ) {


                          File myFile = new File ("Test/a.txt");
                          byte [] mybytearray  = new byte [(int)myFile.length()];
                          System.out.println("length of the file:"+(int)myFile.length());
                          FileInputStream fis = new FileInputStream(myFile);
                          BufferedInputStream bis = new BufferedInputStream(fis);
                          bis.read(mybytearray,0,mybytearray.length);
                          OutputStream os = socket.getOutputStream();
                          System.out.println("Sending " +  "(" + mybytearray.length + " bytes)");
                          os.write(mybytearray,0,mybytearray.length);
                          os.flush();
                          System.out.println("Done.");
                          System.out.println("File updated on the server!!!");


                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }

            } catch (IOException ex) {
                System.err.println("exception-"+ex);
            }

            // /////////  code for detecting the directory changes ends here


     ////fom here
      //FileAlterationObserver observer;



        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
