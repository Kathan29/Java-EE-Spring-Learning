package dateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeDemo {
    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2026, Month.APRIL, 10);
        System.out.println("Date : " + date);
        LocalDate expiryDate = date.plusMonths(5);
        System.out.println("Expiry Date : " + expiryDate);

        System.out.println("Get Year of expiry date : " + expiryDate.getYear());
        System.out.println("Get month of expiry date : " + expiryDate.getMonth());
        System.out.println("Get day of expiry date : " + expiryDate.getDayOfMonth());
        System.out.println("is leap year ? " + expiryDate.isLeapYear());

        LocalDate parseDate = LocalDate.parse("2001-04-29");
        System.out.println(parseDate + " , give month : " + parseDate.getMonth());

        LocalTime time = LocalTime.of(3, 40, 40);
        System.out.println("Hour : " + time.getHour());

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println("date time : " + dateTime);

        // TimeZone ==> ZoneId
        ZonedDateTime zonedGameStartTime = ZonedDateTime.of(dateTime, ZoneId.of("Europe/London"));
        System.out.println("zonedGameStartTime: " + zonedGameStartTime);

        ZonedDateTime indiaTime = zonedGameStartTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        System.out.println("indiaTime: " + indiaTime);

        ZonedDateTime pst = zonedGameStartTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("pst: " + pst);

        // Use-Case 3: Age calculation (Period)

        LocalDate birthDay = LocalDate.of(1978, Month.JANUARY, 1);
        LocalDate today = LocalDate.now(); // current date from system clock
        Period period = birthDay.until(today);
        System.out.println("\nComplete Age: " + period.toString());
        System.out.println("Age: " + period.getYears());

        // Partial Classes
        System.out.println("Christmas: " + MonthDay.of(Month.DECEMBER, 25));
        System.out.println("Credit card expiry date: " + YearMonth.of(2017, Month.JULY));
    }
}
