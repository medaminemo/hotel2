

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;




public class vEng {
    public static Scanner sc ;
    public static boolean verifier_prenom(String ch){
        return ch.matches("[a-zA-Z ]+");
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

    public static boolean verifier_mdp(String mdp){
        if (mdp.length() < 8 || mdp.length() > 20) {
            return false;
        }
        return true;
    }

    public static boolean verifier_tel(String ch){
        return ch.length() == 10;
    }



    public static int newclient(){
        sc = new Scanner(System.in);
        System.out.println("Quelle est votre nom?");
        String last_name=sc.nextLine();
        while(!verifier_prenom(last_name)){
            System.out.println("Veuillez ressaisir votre prénom s'il vous plait");
            last_name=sc.nextLine();
        }

        System.out.println("Quel est votre prenom ?");
        String name=sc.nextLine();
        while(!verifier_prenom(name)){
            System.out.println("Veuillez ressaisir votre nom s'il vous plait");
            name=sc.nextLine();
        }

        System.out.println("Quelle est votre adresse ?");
        sc.nextLine();
        String adresse=sc.nextLine();
        System.out.println(adresse);
        while(!verifier_adresse(adresse)){
            System.out.println("Veuillez ressaisir votre adresse s'il vous plait");
            adresse=sc.nextLine();
        }

        System.out.println("Quel est le nom de la ville ?");
        String ville=sc.next();
        while(!verifier_prenom(ville)){
            System.out.println("Veuillez ressaisir le nom de la ville s'il vous plait");
            ville=sc.next();
        }

        System.out.println("Quel est le nom du pays ?");
        String pays=sc.next();
        while(!verifier_prenom(pays)){
            System.out.println("Veuillez ressaisir le nom de la ville s'il vous plait");
            ville=sc.next();
        }

        System.out.println("what is the address code?");
        String cp=sc.next();
        while(!verifier_cp(cp)){
            System.out.println("Veuillez ressaisir le nom du pays s'il vous plait");
            cp=sc.next();
        }

        System.out.println("Veuillez reseigner votre email");
        String email=sc.next();
        while(!verifier_email(email)){
            System.out.println("Veuillez ressaisir votre e-mail s'il vous plait");
            email=sc.next();
        }

        System.out.println("Quel est votre numéro de téléphone ?");
        String tel=sc.next();
        while(!verifier_tel(tel)){
            System.out.println("Veuillez ressaisir votre numéro de téléphone s'il vous plait");
            tel=sc.next();
        }

        System.out.println("Veuillez reseigner un mot de passe pour votre compte:");
        String mdp=sc.next();
        while (!verifier_mdp(mdp)){
            System.out.println("Veuillez reseigner un mot de passe pour votre compte:");
            mdp=sc.next();
        }
        InsertRecords app = new InsertRecords();
        SelectRecords select = new SelectRecords();
        int id=select.selectMax("id_client","client");
        System.out.println("voici votre numéro d'identificaation: "+id);
        app.insert_client(last_name,name,adresse,ville,cp,pays,email,tel,mdp);
        return id;

    }


    }



