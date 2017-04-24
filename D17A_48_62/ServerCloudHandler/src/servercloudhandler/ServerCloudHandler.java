/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercloudhandler;
/**
 *
 * @author Sagar Palo
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerCloudHandler {

    /**
     * @param args the command line arguments
     */
    
    static int currentlevel = 1;
    static int ntask = 10;
    static int mats = 20;
    static int status[] = new int[10];
    
    public static void main(String[] args) {

        try{
                      
            int port = 5001;
            ServerSocket ss = new ServerSocket(port);
            Socket socket;
            int clientport = 6000;
            ArrayList clients = new ArrayList();
            
            while(true)
            {             
                socket = ss.accept();
                int id = clientport;     
                
                String sendMsg = String.valueOf(clientport)+"\n";
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(sendMsg);
                System.out.println("Message sent to the client is:\nClient Job ID: "+sendMsg);
                bw.flush();
                
                clientport++;               
                
                Client c = new Client(socket.getInetAddress(),id);
                clients.add(c);
                
                socket.close();
                
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            runTask(c);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                
                
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.submit(r);
                
                
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    static void runTask(Client c) throws Exception{
        
        System.out.println(c.getInetAddress()+" "+c.getId());
        ServerSocket ss = new ServerSocket(c.getId());
        Socket socket = ss.accept();
        
        String sendMsg = String.valueOf("sending job")+"\n";
        System.out.println("Current Level "+currentlevel);
        System.out.println(isAllComplete());
        
        if(currentlevel>5){
            sendMsg = "done\n";           
        }
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(sendMsg);
        System.out.println("\nMessage sent to the client is "+sendMsg);
        bw.flush();
        
        int i=0;
        for(i=0;i<ntask;i++){
            if(status[i] == 0){
                break;
            }
        }
        int taskno = i;

        System.out.println("Task no.: " + taskno + " nTask: "+ntask);
            
        if(!sendMsg.equals("done\n") && taskno!=ntask){
            
            double mat1[][] = new double[100][100],mat2[][] = new double[100][100];

            String filename;
            File file;
            if(currentlevel == 1){
                filename = "F:\\Jobs\\Mat"+(taskno*2)+".txt";
                file = new File(filename);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    int j=0;
                    while ((line = br.readLine()) != null) {
                       String linee[] = line.split("\t");
                       for(i=0;i<100;i++){
                           mat1[j][i] = Double.valueOf(linee[i]);
                       }
                       j++;
                    }
                }
                filename = "F:\\Jobs\\Mat"+((taskno*2)+1)+".txt";
                file = new File(filename);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    int j=0;
                    while ((line = br.readLine()) != null) {
                       String linee[] = line.split("\t");
                       for(i=0;i<100;i++){
                           mat2[j][i] = Double.valueOf(linee[i]);
                       }
                       j++;
                    }
                }
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                MatrixOperation mat=new MatrixOperation(mat1,mat2);
                outputStream.writeObject(mat);
                outputStream.flush();

                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                MatrixOperation matresult = (MatrixOperation) inStream.readObject();

                filename = "F:\\Jobs\\Intermediate\\Mat"+((taskno))+".txt";
                file = new File(filename);
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    int k,j;
                    writer.print("");
                    for(k=0;k<100;k++){
                        for(j=0;j<99;j++){
                            String content = String.valueOf(matresult.mat3[k][j])+"\t";
                            writer.append(content);
                            //System.out.println(content);
                        }
                        String content="";
                        if(k==99)
                            content = String.valueOf(matresult.mat3[99][j]);
                        content = String.valueOf(matresult.mat3[99][j])+"\n";
                        writer.append(content);
                        //System.out.println(content);
                    }
                }
            }
            else{
                if((mats) % 2 == 0){
                    //System.out.println(taskno);
                    filename = "F:\\Jobs\\Intermediate\\Mat"+(taskno*2)+".txt";
                    file = new File(filename);
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        int j=0;
                        while ((line = br.readLine()) != null) {
                           String linee[] = line.split("\t");
                           for(i=0;i<100;i++){
                               mat1[j][i] = Double.valueOf(linee[i]);
                           }
                           j++;
                        }
                    }
                    filename = "F:\\Jobs\\Intermediate\\Mat"+((taskno*2)+1)+".txt";
                    file = new File(filename);
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        int j=0;
                        while ((line = br.readLine()) != null) {
                           String linee[] = line.split("\t");
                           for(i=0;i<100;i++){
                               mat2[j][i] = Double.valueOf(linee[i]);
                           }
                           j++;
                        }
                    }
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        MatrixOperation mat=new MatrixOperation(mat1,mat2);
                        outputStream.writeObject(mat);
                        outputStream.flush();

                        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                        MatrixOperation matresult = (MatrixOperation) inStream.readObject();

                        filename = "F:\\Jobs\\Intermediate\\Mat"+((taskno))+".txt";
                        file = new File(filename);
                        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                            int k,j;
                            writer.print("");
                            for(k=0;k<100;k++){
                                for(j=0;j<99;j++){
                                    String content = String.valueOf(matresult.mat3[k][j])+"\t";
                                    writer.append(content);
                                    //System.out.println(content);
                                }
                                String content="";
                                if(k==99)
                                    content = String.valueOf(matresult.mat3[99][j]);
                                content = String.valueOf(matresult.mat3[99][j])+"\n";
                                writer.append(content);
                                //System.out.println(content);
                            }
                        }
                }
                else{            
                    if(mats == 1){
                        
                    }
                    else if(taskno*2 != mats-1){
                        
                        System.out.println("here1");
                        filename = "F:\\Jobs\\Intermediate\\Mat"+(taskno*2)+".txt";
                        file = new File(filename);
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            int j=0;
                            while ((line = br.readLine()) != null) {
                               String linee[] = line.split("\t");
                               for(i=0;i<100;i++){
                                   mat1[j][i] = Double.valueOf(linee[i]);
                               }
                               j++;
                            }
                        }
                        filename = "F:\\Jobs\\Intermediate\\Mat"+((taskno*2)+1)+".txt";
                        file = new File(filename);
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            int j=0;
                            while ((line = br.readLine()) != null) {
                               String linee[] = line.split("\t");
                               for(i=0;i<100;i++){
                                   mat2[j][i] = Double.valueOf(linee[i]);
                               }
                               j++;
                            }
                        }
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        MatrixOperation mat=new MatrixOperation(mat1,mat2);
                        outputStream.writeObject(mat);
                        outputStream.flush();

                        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                        MatrixOperation matresult = (MatrixOperation) inStream.readObject();

                        filename = "F:\\Jobs\\Intermediate\\Mat"+((taskno))+".txt";
                        file = new File(filename);
                        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                            int k,j;
                            writer.print("");
                            for(k=0;k<100;k++){
                                for(j=0;j<99;j++){
                                    String content = String.valueOf(matresult.mat3[k][j])+"\t";
                                    writer.append(content);
                                    //System.out.println(content);
                                }
                                String content="";
                                if(k==99)
                                    content = String.valueOf(matresult.mat3[99][j]);
                                content = String.valueOf(matresult.mat3[99][j])+"\n";
                                writer.append(content);
                                //System.out.println(content);
                            }
                        }
                    }
                    else{
                        
                        //System.out.println("here2");
                        String src = "F:\\Jobs\\Intermediate\\Mat"+((mats-1))+".txt";
                        String dest = "F:\\Jobs\\Intermediate\\Mat"+(taskno)+".txt";
                        File infile =new File(src);
                        BufferedReader br = new BufferedReader(new FileReader(infile));
                        File outfile =new File(dest);
                        PrintWriter wr = new PrintWriter(outfile);
                        wr.write("");
                        copyFileCharByChar(br,wr);
                    }
                }
            }
            
            status[taskno] = 1;
            printStatus();
            
            if(isAllComplete()){
                currentlevel++;
                ntask = (int)Math.ceil(ntask/2.0);
                mats = (int)Math.ceil(mats/2.0);
                //Scanner scr=new Scanner(System.in);
                //scr.nextInt();
                int k=0;
                for(k=0;k<10;k++)
                    status[k]=0;
                
                System.out.println("\n\nNew Status>>>");             
                printStatus();
                //System.out.println("hi");
                if(currentlevel == 6){
                    String src = "F:\\Jobs\\Intermediate\\Mat0.txt";
                    String dest = "F:\\Jobs\\Result.txt";
                    File infile =new File(src);
                    BufferedReader br = new BufferedReader(new FileReader(infile));
                    File outfile =new File(dest);
                    PrintWriter wr = new PrintWriter(outfile);
                    wr.write("");
                    copyFileCharByChar(br,wr);
                }
                //System.out.println("hi2");
            }
            //System.out.println("hi3");
        }
        //System.out.println("hi4");
        
        socket.close();
    }
    
    static void copyFileCharByChar(BufferedReader rd, PrintWriter wr) {
        try {
            while(true) {
                int ch = rd.read();
                if(ch == - 1) break;
                wr.write(ch);
            }
          } catch(Exception ex) {
            throw new RuntimeException(ex.toString());
        }
        finally{
            wr.close();
        }
    }
    
    static void printStatus(){
        int i=0;
        System.out.println("Current Level: "+currentlevel+" | nTask: "+ntask+" | Mats: "+mats);
        for(i=0;i<ntask;i++){
            System.out.print(status[i]+" ");
        }
    }
    
    static boolean isAllComplete(){
        int i=0;
        for(i=0;i<ntask;i++){
            if(status[i]==0){
                return false;
            }
        }
        return true;
    }
}

class Client{
    
    InetAddress ip;
    int id;
    
    Client(InetAddress ip, int id){
        this.ip = ip;
        this.id = id;
    }
    
    InetAddress getInetAddress(){
        return ip;
    }
    
    int getId(){
        return id;
    }
}

class MatrixOperation implements Serializable{
    
    double mat1[][];
    double mat2[][];
    double mat3[][];
    static final long serialVersionUID =0L;
    
    MatrixOperation(double mat1[][],double mat2[][]){
        this.mat1 = mat1;
        this.mat2 = mat2;
        this.mat3 = new double[100][100];
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
}
