import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class reservation {
    public static Scanner sc;
    public static SelectRecords s;
    public static boolean verifier_date(String s){
        if (!s.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;
        }
        return true;
    }
    public static boolean estapres(String dateString1, String dateString2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(dateString1);
        LocalDate date2 = LocalDate.parse(dateString2);
        return date1.isAfter(date2);


    }public static boolean isNotPastDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
            LocalDate today = LocalDate.now();
            return !date.isBefore(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static int reserver(int id ) throws ParseException {
        sc = new Scanner(System.in);
        s = new SelectRecords();
        int i = s.selectMax("id_reservation", "reservation") + 1;
        System.out.println("Donnez le num de l'hotel ou vous voulez reserver");
        System.out.println(s.selecthotel());

        int x = sc.nextInt();
        while (x < 1 || x > 4) {
            System.out.println("Donnez le num de l'hotel ou vous voulez reserver");
            System.out.println(s.selecthotel());
            x = sc.nextInt();
        }
        System.out.println("Donnez le le numero de la chambre que vous voulez reserver");
        System.out.println(s.selectchambre(x));
        int chambre = sc.nextInt();
        int max_c = s.selectNumChambre(x);
        while (chambre < 0 || chambre > max_c) {
            System.out.println("Donnez le le numero de la chambre que vous voulez reserver");
            System.out.println(s.selectchambre(x));
            chambre = sc.nextInt();
        }
        boolean verification = false;
        String d;
        String h;
        do {
            System.out.println("Selectionne la date du debut de votre sejour ");
            d = sc.next();

            while (!verifier_date(d) || !isNotPastDate(d)) {
                System.out.println("Selectionne la date du debut de votre sejour\n ATTENTION IL FAUT QUE LA DATE SOIT SUR CE FORMAT: AAAA-MM-JJ ");
                d = sc.next();
            }

            System.out.println("Selectionne la date du fin de votre sejour\n ATTENTION IL FAUT QUE LA DATE SOIT SUR CE FORMAT: AAAA-MM-JJ\n ET IL FAUT QUE LA DATE SOIT PLUS TARD DE LA PREMIERE ");
            h = sc.next();
            while (!verifier_date(h) || !estapres(h, d)) {
                System.out.println(!estapres(h, d));
                System.out.println("Selectionne la date du fin de votre sejour\n ATTENTION IL FAUT QUE LA DATE SOIT SUR CE FORMAT: AAAA-MM-JJ\n ET IL FAUT QUE LA DATE SOIT PLUS TARD DE LA PREMIERE ");
                h = sc.next();
            }
            verification = s.verifier_dispo(d, h, chambre, x);
        } while (verification == false);
        //ICI

        InsertRecords p = new InsertRecords();
        p.insert_reservation(i, id, d, h, x);


        return i;
    }


    }

