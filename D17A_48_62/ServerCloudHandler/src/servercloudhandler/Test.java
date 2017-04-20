/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercloudhandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Sagar Palo
 */
public class Test {

    public static void main(String...args)throws Exception{
        String src = "F:\\Jobs\\Mat0.txt";
                    String dest = "F:\\Jobs\\Results.txt";
                    File infile =new File(src);
                    BufferedReader br = new BufferedReader(new FileReader(infile));
                    File outfile =new File(dest);
                    PrintWriter wr = new PrintWriter(outfile);
                    wr.write("");
                    
                    String line;
                    int k=0;
                    int l=0;
                    copyFileCharByChar(br,wr);
                    
                    
                    
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

}
