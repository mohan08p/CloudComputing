/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercloudhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Sagar Palo
 */
public class CreateJob {

    public static void main( String[] args )
    {
        int i=0,j=0,k=0;
    	try {
            
            for(i=0;i<20;i++){
                String filename = "F:\\Jobs\\Mat"+i+".txt";
                File file = new File(filename);
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                for(k=0;k<100;k++){
                    for(j=0;j<99;j++){
                        String content = String.valueOf(Math.random()*1000)+"\t";
                        writer.append(content);
                        System.out.println(content);
                    }
                    String content="";
                    content = String.valueOf(Math.random()*1000)+"\n";
                    writer.append(content);
                    System.out.println(content);
                }
                System.out.println("Done for File "+i);
                writer.close();
            }
            
            String filename = "F:\\Jobs\\Result.txt";
            File file = new File(filename);
            System.out.println(file);
            PrintWriter writer = new PrintWriter(file);
            writer.print("");

    	} 
        catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}
