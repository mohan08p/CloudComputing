/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercloudhandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Sagar Palo
 */
public class TestClient {
    
    private static Socket socket;
 
    public static void main(String args[])
    {
        try
        {
            while(true){
                String host = "localhost";
                int port = 5000;
                InetAddress address = InetAddress.getByName(host);
                socket = new Socket(address, port);

                /*OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                String number = "2";

                String sendMessage = number + "\n";
                bw.write(sendMessage);
                bw.flush();
                System.out.println("Message sent to the server : "+sendMessage);*/

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
                System.out.println("Message received from the server : " +message);

                port = Integer.parseInt(message);
                socket.close();

                socket = new Socket(address, port);
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                message = br.readLine();
                System.out.println("Message received from the server : " +message);

                if(message.equals("done")){
                    break;
                }
                
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                MatrixOperation mat = (MatrixOperation) inStream.readObject();
                mat.multiply();

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(mat);
                outputStream.flush();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
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
/*class MatrixOperation implements Serializable{
    
    double mat1[][];
    double mat2[][];
    double mat3[][];
    
    MatrixOperation(double mat1[][],double mat2[][]){
        this.mat1 = mat1;
        this.mat2 = mat2;
        this.mat3 = new double[10][10];
    }
    
    void multiply(){
        int i,j,k;
        int n = mat1.length;
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++)
            {
                for (k = 0; k < n; k++)
                {
                    mat3[i][j] = mat3[i][j] + mat1[i][k] * mat2[k][j];
                }
            }
        }
    }
}*/
