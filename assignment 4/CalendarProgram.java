import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class CalendarProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = sc.nextInt();

        System.out.print("Enter month (1-12): ");
        int month = sc.nextInt();

        LocalDate firstDay = LocalDate.of(year, month, 1);
        int totalDays = firstDay.lengthOfMonth();
        DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
        int start = dayOfWeek.getValue(); // Monday=1 ... Sunday=7

        String monthName = firstDay.getMonth().toString();
        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();

        System.out.println("\nCalendar for the month of " + monthName + ", " + year);
        System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

        int spaceCount = start % 7;
        for (int i = 0; i < spaceCount; i++) {
            System.out.print("    ");
        }

        for (int day = 1; day <= totalDays; day++) {
            System.out.printf("%-4d", day);
            if ((day + spaceCount) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
        sc.close();
    }
}
