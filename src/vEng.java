

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;




public class vEng {
    public static Scanner sc ;
    public static boolean verifier_prenom(String ch){
        return ch.matches("[a-zA-Z]+");
    }

    public static boolean verifier_adresse(String adresse) {
        String regex = "^\\d+\\s+[A-Za-z]+\\s+([A-Za-z0-9\\s\\-']+$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(adresse);
        System.out.println(matcher.matches());
        return matcher.matches();
    }



    public static boolean verifier_cp(String ch){

        return ch.length()==5;
    }
    public static boolean verifier_email(String ch){
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return ch.matches(regex);
    }

    public static boolean verifier_tel(String ch){
        return ch.length() == 10;
    }



    public static int newclient(){
        sc = new Scanner(System.in);
        System.out.println("what's his/her name ?");
        String last_name=sc.next();
        while(!verifier_prenom(last_name)){
            System.out.println("sorry there's something wrong with the last name");
            last_name=sc.next();
        }

        System.out.println("what's his name ?");
        String name=sc.next();
        while(!verifier_prenom(name)){
            System.out.println("sorry there's something wrong with the name");
            name=sc.next();
        }

        System.out.println("what's the address ?");
        sc.nextLine();
        String adresse=sc.nextLine();
        System.out.println(adresse);
        while(!verifier_adresse(adresse)){
            System.out.println("sorry there's something wrong with the adress");
            adresse=sc.nextLine();
        }

        System.out.println("where does he live (name of the city)?");
        String ville=sc.next();
        while(!verifier_prenom(ville)){
            System.out.println("sorry there's something wrong with the name of the city");
            ville=sc.next();
        }

        System.out.println("where does he live(country)?");
        String pays=sc.next();
        while(!verifier_prenom(pays)){
            System.out.println("sorry there's something wrong with the name of the city");
            ville=sc.next();
        }

        System.out.println("what is the address code?");
        String cp=sc.next();
        while(!verifier_cp(cp)){
            System.out.println("sorry there's something wrong with the address code");
            cp=sc.next();
        }

        System.out.println("what is the e-mail?");
        String email=sc.next();
        while(!verifier_email(email)){
            System.out.println("sorry there's something wrong with the e-mail");
            email=sc.next();
        }

        System.out.println("what is the phone number?");
        String tel=sc.next();
        while(!verifier_tel(tel)){
            System.out.println("sorry there's something wrong with the phone number");
            tel=sc.next();
        }
        InsertRecords app = new InsertRecords();
        SelectRecords select = new SelectRecords();
        int id=select.selectMax("id_client","client");
        app.insert_client(last_name,name,adresse,ville,cp,pays,email,tel);
        return id;

    }


    }



