package ru.kuznetsovka.gifcurrency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1L);
        return yesterday.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
