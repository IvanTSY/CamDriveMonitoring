package lib.logging;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;

public class SuperVisor {

    public void checkerEmptyFile(String keyWord, String filePath) throws IOException {
        File asd = new File(filePath);
        LineIterator lineIterator = FileUtils.lineIterator(asd, "UTF8");

        String lastLine = "";

        while (lineIterator.hasNext()) {
            lastLine = lineIterator.nextLine();
        }

        lineIterator.close();

        System.out.println("Ключ " + lastLine);

        System.out.println("Путь " + asd);

        System.out.println("Сравнил " + lastLine.equals(keyWord));


        if (lastLine.equals(keyWord)) {
            System.out.println("Удалил " + asd.delete());
            asd.delete();

        }

    }


//Как Алексей советовал врубать поток

//    private void timeThread() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int timeTest = 30;
//                long timerWorkTest = System.currentTimeMillis();
//                while ((System.currentTimeMillis()-timerWorkTest) < timeTest){
//                    System.out.println("The end of test after"+(timeTest-(System.currentTimeMillis()-timerWorkTest)));
//                }
//
//
//                //...
//                // логика которая будет выполняться в этом потоке
//                //...
//            }
//        }).start();
//    }


    public static String logCat() throws IOException {
//        Session.SessionInactivityTimer

//Thread thread = new Thread();
//thread.start();
//thread.start();

//      String command = "adb logcat| find 'D MtkOmxVdecEx:'"  для хрома
        String command = "adb logcat com.camdrive/presentation.gui.online.PlaybackActivity";
        Process process = Runtime.getRuntime().exec(command);


        InputStream inputStream = process.getInputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String log = null;
        //String loger;
        int countMessages = 0;
        int timeOfTest = 15000;

        long timeStartOutOfPresent = System.currentTimeMillis();
        long timeIsVideoNotPlaying = 0;


        int i = 0;
        long xxx = -1;

        while ((((log = bufferedReader.readLine()) != null)&(10000 <= System.currentTimeMillis() - timeStartOutOfPresent))) {
            i++;
            if (log.contains("beward_media_player: statistic videoframe:")) {
            //    loger = log;
                System.err.println(log);
                countMessages++;
                timeStartOutOfPresent = System.currentTimeMillis();
            } else {
                timeIsVideoNotPlaying = System.currentTimeMillis() - timeStartOutOfPresent;
            }
            long breaker = System.currentTimeMillis() - timeStartOutOfPresent;
            if (breaker >= timeOfTest) {
                break;
            }
            if (xxx/1000 != timeIsVideoNotPlaying/1000) {

                if(timeIsVideoNotPlaying < xxx){
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                    System.err.println("=================================================");
                }

                xxx = timeIsVideoNotPlaying;
                System.out.println(timeIsVideoNotPlaying/1000);
            }
        }

        inputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        System.err.println("Log = " + log);
        System.err.println("Proc = " + inputStream);
        return null;
    }


}
