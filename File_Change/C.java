import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.*;

public class C {
  public static void main(String[] argv) throws Exception {
    Socket sock = new Socket("127.0.0.1", 23456);
    byte[] mybytearray = new byte[1024];
    InputStream is = sock.getInputStream();
    FileOutputStream fos = new FileOutputStream("s.txt");
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    System.out.println(Arrays.toString(mybytearray));
    bos.close();
    sock.close();
  }
}
