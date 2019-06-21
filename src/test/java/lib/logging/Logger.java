package lib.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static void errorLog(String fileName, String message) throws IOException {
        FileWriter errorFile = new FileWriter(fileName,true);
        errorFile.write(message);
        errorFile.close();
    }
}
