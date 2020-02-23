//Zain Imran, CISC 3130-TY9

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

 
public class ProjectOne {
    public static void main(String args[] ) throws Exception {
        TrackData w = new TrackData(); //this creates a new TrackData object, TrackData is defined below
        w.readFile(); //calls the TrackData.readFile() method, which reads all artists from the file
        w.reportArtists();
    }
}
/*
 * this class represents array structure of data in file in terms of rows and columns
 */
class TrackData{
    int cols;
    int rows;
    String[][] data; //this holds the data from the file
 
    public TrackData(){
        this.cols = 5; //default length and width for the array
        this.rows = 5;
        this.data = new String[rows][cols];
    }
    /*
     * Read file contents into data array
     */
    public void readFile(){
        File text = new File("C:/folder/spotifyGlobal.csv"); //open the file, this might have to be changed for your csv file
        Scanner scnr;
        try {
            scnr = new Scanner(text); //creates a text scanner for the file, I may have used Scanner(System.in) for user input from the console
            int row =0, col=0;
           
           scnr.nextLine();
           scnr.nextLine();
           
            while(scnr.hasNextLine()){ //check if the file has another line, if the file does have another line, continue reading from the file
                String line = scnr.nextLine(); //get the next line from the file
                // System.out.println("" + line);
               String[] strArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //split the line into its values using the commas as a seperator, "a,b,c" becomes ["a", "b", "c"]
                System.out.println(line + "                " + Arrays.toString(strArr));
               
                data[row][col] = strArr[col];col++; //add all the data to array col++ increases the position of col each time
                data[row][col] = strArr[col];col++;
                data[row][col] = strArr[col];col++;
                data[row][col] = strArr[col];col++;
                data[row][col] = strArr[col];col++;
                //System.out.println(data[row][2]);
                col=0;row++; //reset the coloumn back to the start, and increase the row (start a new row for each line)
 
            }
 
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("students.dat file not found");
        }catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            System.out.println("students.dat file has wrong data");
        }
    }
    /*
     * create two reports
     * 1. which artists appear how many times
     * 2. Top streamed artists
     */
    public void reportArtists(){
 
        PrintWriter writer1 =null; //create printwriter variables to print to a file
        PrintWriter writer2 =null;  
        try {
            writer1 = new PrintWriter(new FileWriter("artists.dat"));
            writer2 = new PrintWriter(new FileWriter("TopStramedArtists.dat")); //Instantiate printwriters
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(" file not found");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(" file is not accesible");
        }
       
        String[] artistsArr = new String[rows];
        int[] count = new int[rows];
        int[] streamCount = new int[rows];
        int index = 0;
        int atWhichIndex=-1;
 
        for(int i=0;i<rows;i++){
 
            // System.out.println(data[i][2]);
            atWhichIndex = getArtistsExistsIndex(artistsArr,data[i][2]);
            if(atWhichIndex<0){
                artistsArr[index] = data[i][2];
                count[index]=count[index]+1;
                streamCount[index]=streamCount[index]+Integer.parseInt(data[i][3]);
                index++;
            }else{
                count[atWhichIndex]=count[atWhichIndex]+1;
                streamCount[atWhichIndex]=streamCount[atWhichIndex]+Integer.parseInt(data[i][3]);
            }
        }
        // Arrays.sort(streamCount);
        for(int i=0;i<index;i++){
            writer1.write(artistsArr[i]+","+count[i]+"\n");
            writer2.write(artistsArr[i]+","+streamCount[i]+"\n");  
        }
 
 
 
        writer1.flush();
        writer1.close();
        writer2.flush();
        writer2.close();
 
    }
 
 
    /*
     * get position of artists in array
     */
    public int getArtistsExistsIndex(String[] arr, String artist){
        int result=-1;
        for(int i=0;i<rows;i++){
            if(artist.equals(arr[i])){
 
                return i;
            }
        }
        return result;
    }
 
 
 
}
 
class Artist{
    String name;
    Artist next;
}
 
class topStreamingArtists{
    private Artist first;
}
