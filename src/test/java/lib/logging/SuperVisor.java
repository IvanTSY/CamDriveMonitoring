package lib.logging;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.rules.Timeout;

import java.io.*;
//@SuppressWarnings("ALL")

public class SuperVisor {

    public void checkerEmptyFile(String keyWord, String filePath) throws IOException {
        File asd = new File(filePath);
        LineIterator lineIterator = FileUtils.lineIterator(asd,"UTF8");

        String lastLine = "";

        while (lineIterator.hasNext()){
            lastLine =  lineIterator.nextLine();
        }

        lineIterator.close();

        System.out.println("Ключ "+lastLine);

        System.out.println("Путь "+asd);

        System.out.println("Сравнил "+lastLine.equals(keyWord));


        if(lastLine.equals(keyWord)){
            System.out.println("Удалил "+asd.delete());
            asd.delete();

        }

    }

    public static String logCat() throws IOException {
//|grep statistic"
        String command = "adb logcat com.camdrive/presentation.gui.online.PlaybackActivity";
        Process process = Runtime.getRuntime().exec(command);

        InputStream inputStream = process.getInputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String log;
        int i = 0;
        String logFrame = null;

        while (((log = bufferedReader.readLine()) != null)) {

            if ((log.contains("beward_media_player: statistic videoframe:"))) {

                logFrame = bufferedReader.readLine();
                System.err.println(log);
                i++;
            } else {
                i++;
                System.err.println("Video not playing 5sec");
            }
            if (i == 1000) {
                System.err.println("The end");
                break;
            }

        }

        inputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        System.err.println("Log = " + log);
        System.err.println("Proc = " + inputStream);
        return logFrame;
    }


}
