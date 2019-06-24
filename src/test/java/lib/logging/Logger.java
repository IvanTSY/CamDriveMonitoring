package lib.logging;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static int errorLog(String fileName, String message, int i) throws IOException {
        FileWriter errorFile = new FileWriter(fileName,true);
        errorFile.write(message);
        errorFile.close();
        return i++;
    }
}
