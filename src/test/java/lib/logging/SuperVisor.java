package lib.logging;

import lib.ui.CamDrivePageObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb.AllCameraIOSArchiveTests;

import java.io.File;
import java.io.IOException;

public class SuperVisor {


    public void checkerEmptyFile(String keyWord, String filePath) throws IOException {
        LineIterator lineIterator = FileUtils.lineIterator(new File(filePath),"UTF8");

        File del = new File(filePath);
        String lastLine = "";

        while (lineIterator.hasNext()){
            lastLine =  lineIterator.nextLine();
        }

        System.out.println(lastLine);
        System.out.println(lastLine.equals(keyWord));
        System.out.println(filePath);
        boolean asd = lastLine.equals(keyWord);

        if (asd==true){
            del.delete();
        }
    }
}
