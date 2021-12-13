import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO
     * create a static LocationMap object
     */

    private static LocationMap locationMap;

    /** TODO
     * create a vocabulary HashMap to store all directions a user can go
     */

    private static HashMap<String, String> vocabulary = new HashMap<String,
        String>();




    /** TODO
     * create a FileLogger object
     */
    private FileLogger fileLogger = new FileLogger();

    /** TODO
     * create a ConsoleLogger object
     */

    private ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */

        vocabulary.put("North", "N");
        vocabulary.put("North East", "NE");
        vocabulary.put("North West", "NW");
        vocabulary.put("South", "S");
        vocabulary.put("North East", "NE");
        vocabulary.put("South West", "SW");
        vocabulary.put("East", "E");
        vocabulary.put("West", "W");

    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */

        Scanner sc = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */

        int locationID = INITIAL_LOCATION;

        /** TODO
         * get the location and print its description to both console and file
         * use the FileLogger and ConsoleLogger objects
         */


        String path = System.getProperty("user.dir") + File.separator +
                      "locations.txt";
        HashMap<Integer,String> locations = new HashMap<Integer,String>();
        try{
            File f  = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line = br.readLine()) != null){
                String []vals = line.split(",");
                locations.put(Integer.valueOf(vals[0]),vals[1]);

            }
            br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

 boolean ok = true;
        while (ok) {
        //while (true) {
            /** TODO
             * verify if the location is exit
             */

            Set set = locations.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                if(((Integer)entry.getKey()).intValue() == locationID){
                    String descr = (String)entry.getValue();
                    consoleLogger.log(descr);
                    fileLogger.log(descr);
                    if(descr.equalsIgnoreCase("Dead End")){
                        System.out.println("This is a dead end");
                    }
                    break;
                }
            }

            /** TODO
             * get a map of the exits for the location
             */

            LinkedHashMap<String, Integer> exits = new LinkedHashMap <String,
                Integer>();
            try{
                path = System.getProperty("user.dir") + File.separator +
                                              "directions.txt";
                File f  = new File(path);
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while((line = br.readLine()) != null){
                    String []vals = line.split(",");
                    if(locationID == Integer.parseInt(vals[0])){
                        exits.put(vals[1],Integer.valueOf(vals[2]));
                    }
                }
                br.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
             System.out.print("please enter a direction which you want to " +
                                "go: ");
            String directionLetter = sc.next().toUpperCase();
            System.out.println("");

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            consoleLogger.log("possible exits for Location: "+ locations.get(locationID));
            fileLogger.log("possible exits for Location: "+ locations.get(locationID));
            set = exits.entrySet();
            iterator = set.iterator();
            int index =1;
            Integer foundExitID= -1;
            String foundExitDescr= null;
            while(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                Integer exitID = (Integer)entry.getValue();
                consoleLogger.log(index+". "+locations.get(exitID));
                fileLogger.log(index+". "+locations.get(exitID));
                index++;
                if(directionLetter.equals((String)entry.getKey())){
                    foundExitID = (Integer)entry.getValue();
                    foundExitDescr = locations.get(foundExitID);
                }
            }
            if(foundExitID > -1){
                //System.out.println(foundExitDescr);
                locationID = foundExitID;
                ok=true;
            }else{
                String error = "\"Unfortunately you cannot proceed in that " +
                               "direction";
                consoleLogger.log(error);
                fileLogger.log(error);
                ok=false;
            }

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */

            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */

        new Mapping().mapping();



    }

}
