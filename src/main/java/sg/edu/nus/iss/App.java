package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    // e.g. java.sg.edu.nus.iss.App arg[0] arg[1]...
    // e.g. java sg.edu.nus.iss.App "c:\data" myfile.txt
        if (args.length>0){
            for (String arg: args){
                System.out.println(arg);
            }
        }else{
            System.out.println("You have not passed in any argument");
        }

        String dirPath = args[0];
        String fileName = args[1];
        // use file.separator so java handles the \ instead of trying to handle windows and mac
        String dirPathFileName = dirPath + File.separator + fileName;

        //use file object to check if the directory exists
        // create the directory if doesn't exist
        //Slide 3
        File newDirectory = new File(dirPath);
        // use java.io.file documentation
        if (!newDirectory.isDirectory()){
            newDirectory.mkdir();
        }

        File fileData = new File(dirPathFileName);

        if (fileData.exists()){
            System.out.println(dirPathFileName + " already exists");
        }else{
            fileData.createNewFile();
        }

        String content = "I would like to go home early to rest and start coding practice again.";
        String content2 = "Let's keep on coding coding coding...";

        FileWriter fileWriter = new FileWriter(dirPathFileName, true);
        fileWriter.write(content);
        fileWriter.write("\n" + content2);
        fileWriter.flush();
        fileWriter.close();

        //another example - a typ eof decorator pattern
        FileWriter fw2 = new FileWriter(dirPathFileName,true);
        String content3 = "a quick brown fox jumps over the wall";
        BufferedWriter bw = new BufferedWriter(fw2);
        bw.append(content3);
        bw.flush();
        bw.close();
        fw2.close();

        //another example
        String content4 = "It's good to buy a macbook with 32GB for your development.";
        FileOutputStream fos = new FileOutputStream(dirPathFileName, true);
        byte[]byteContent = content4.getBytes();
        fos.write(byteContent);
        fos.flush();
        fos.close();

        // another example - with decorator pattern
        String content5 = "Hello!!!!!";
        FileOutputStream fos2 = new FileOutputStream(dirPathFileName, true);
        DataOutputStream dos2 = new DataOutputStream(fos2);
        dos2.writeUTF(content5);
        dos2.flush();
        dos2.close();

        // reading from file
        File file2 = new File(dirPath + File.separator + fileName);
        FileReader fr = new FileReader(file2);
        int dataRead = fr.read();
        while (dataRead!= -1){
            System.out.print((char)dataRead);
            dataRead = fr.read();
        }

       

        // another example - using BufferedReader
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        while (line!=null){
            System.out.println(line);
            line = br.readLine();
        }
        fr.close();
        br.close();

        //another Example - FileInputStream and DataInputStream - a type of decorator
        FileInputStream fis = new FileInputStream(file2);
        DataInputStream dis = new DataInputStream(fis);
        int disByte = dis.read();
        while(disByte!=-1){
            System.out.print((char)disByte);
            disByte=dis.read();
        }
        dis.close();
        fis.close();
    }
}
