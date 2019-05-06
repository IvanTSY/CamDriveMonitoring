package tests;

import org.junit.Test;

import java.util.Calendar;

public class CalendarTest {
    int currentDay;
    int currentMonth;
    int currentYear;
    int currentHour;

    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    String currentHourCONVERTED;
@Test
    public void Calendar() {
    currentHour = Calendar.getInstance().getTime().getHours();
    currentDay = Calendar.getInstance().getTime().getDate();
    currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    currentYear = Calendar.getInstance().getWeekYear();

//==========================================================
    if (currentDay <10){
        currentDayCONVERTED = "0"+currentDay;
    }else currentDayCONVERTED = Integer.toString(currentDay);
//==========================================================
    if (currentMonth <10){
        currentMonthCONVERTED = "0"+currentMonth;
    }else currentMonthCONVERTED = Integer.toString(currentMonth);
//==========================================================
    if (currentHour <10){
        currentHourCONVERTED = "0"+currentHour;
    }else currentHourCONVERTED = Integer.toString(currentHour);



    System.out.println(currentDayCONVERTED);
    System.out.println(currentMonthCONVERTED);
    System.out.println(currentHourCONVERTED);


    }
}
