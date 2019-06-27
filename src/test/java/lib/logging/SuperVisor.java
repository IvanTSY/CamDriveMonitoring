package lib.logging;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

}
