import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";

    static {

        /** TODO
         * create a new File object for FILE_LOGGER_NAME
         * if the file already exists, delete it first
         * use try/catch block
         */

        try {
            String path = System.getProperty("user.dir");
            File   f    = new File(path + File.separator + FILE_LOGGER_NAME);
            if (f.exists()) {
                f.delete();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void log (String message) {
        /** TODO
         * create a new FileWriter in append mode
         * write the message to file
         * check the ExpectedOutput files
         * use try-with-resources/catch block
         */

        String path =
            System.getProperty("user.dir")+File.separator + FILE_LOGGER_NAME;
               FileWriter fw = null;
        try{
            File f = new File(path);
            if(f.exists()){
                fw = new FileWriter(path, true);
                fw.write(message);
                fw.write("\n");
            }else {
                f.createNewFile();
                fw = new FileWriter(path, true);
                fw.write(message);
                fw.write("\n");
            }
            fw.close();
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
           /*System.out.println("Message has been written successfully into " +
                                "the " +
                               "block file");

            */
        }

    }

}
