package com.lms.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CalulateFine{
        public static String  getFine(LocalDateTime dueDate){
            long daysDue =  LocalDate.now().until(dueDate, ChronoUnit.DAYS);
            if(daysDue>0){
                return String.valueOf(0.25 * daysDue);
            }
            return String.valueOf(0);
        }
    }