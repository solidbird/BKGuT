package com.example.bkgut;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.roomorama.caldroid.CaldroidFragment;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Termine extends Fragment {

    String OOF = "https://www.bkgut.de";
    Date oof;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.content_termine, container,false);

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.add(Calendar.MONTH, -2);
        min.set(Calendar.DAY_OF_MONTH, 1);
        max.add(Calendar.YEAR, 1);

        AgendaCalendarView acv = (AgendaCalendarView) vw.findViewById(R.id.OOF_cal);
        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        acv.init(eventList, min, max, Locale.getDefault(), new CalendarPickerController() {
            @Override
            public void onDaySelected(DayItem dayItem) {
                System.out.println("DAYYY SELECTED");
            }

            @Override
            public void onEventSelected(CalendarEvent event) {
                System.out.println("EVENT");
            }

            @Override
            public void onScrollToDate(Calendar calendar) {

            }
        });

        return vw;
    }

    private void mockList(List<CalendarEvent> eventList){
        Calendar startTime1 = Calendar.getInstance();
        //Wed Mar 13 20:12:23 GMT+01:00 2019

        Date dt = new Date(119, 3,10,0,0,0);
        startTime1.setTime(dt);

        System.out.println(startTime1.getTime());

        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.DAY_OF_WEEK, 0);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Sterben gehen!", "aaaaaaa","Alsdorf", ContextCompat.getColor(getActivity().getApplicationContext(),R.color.colorPrimary), startTime1, null, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        Calendar endTime2 = Calendar.getInstance();
        endTime1.add(Calendar.DAY_OF_WEEK, 0);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Noch mehr sterben gehen!", "aaaaaaa","Aachen", ContextCompat.getColor(getActivity().getApplicationContext(),R.color.blue_selected), startTime2, endTime2, true);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        endTime1.add(Calendar.DAY_OF_WEEK, 0);
        BaseCalendarEvent event3 = new BaseCalendarEvent("Existieren!", "aaaaaaa","Universum", ContextCompat.getColor(getActivity().getApplicationContext(),R.color.caldroid_light_red), startTime3, endTime3, true);
        eventList.add(event3);

    }
}
