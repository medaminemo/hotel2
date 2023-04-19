import java.io.IOException;
import java.util.Scanner;

public class Prestation {
    public static Scanner sc;
    public static SelectRecords s;
    public static InsertRecords i;



        public static  void switc(int x,int w){
            switch (x){
                case 1:
                    i.insert_facture(w,"Dejeuner");
                    break;
                case 2:
                    i.insert_facture(w,"Internet");
                    break;
                case 3:
                    i.insert_facture(w,"Parking");
                    break;
                case 4:
                    i.insert_facture(w,"Piscine");
                    break;
                case 5:
                    i.insert_facture(w,"Dejeuner");
                    break;
            }
        }
        public static void ajout_prestation(int w) throws IOException {
            sc= new Scanner(System.in);
            i=new InsertRecords();
            System.out.println("Est ce que vous voulez decouvrir nos prestation");
            String c=sc.next();
            s=new SelectRecords();
            int x=c.compareTo("oui");
            if ( x==0){
                String k=s.select_prestation();
                System.out.println(k);
                System.out.println("De que est que vous voulez beneficier(Inserez le num de la prestation):");
                x=sc.nextInt();
                while (!(x>1&&x<5)){
                    System.out.println("De que est que vous voulez beneficier(Inserez le num de la prestation entre 1 et 5):");
                    x=sc.nextInt();
                }
                switc(x,w);
                System.out.println("Est ce que vous voulez rajouter autre chose?");
                c=sc.next();
                x=c.compareTo("oui");
                while(x==0){
                    System.out.println("De que est que vous voulez beneficier(Inserez le num de la prestation):");
                    x=sc.nextInt();
                    while (!(x>1&&x<5)){
                        System.out.println("De que est que vous voulez beneficier(Inserez le num de la prestation entre 1 et 5):");
                        x=sc.nextInt();
                    }
                    switc(x,w);
                    System.out.println("Est ce que vous voulez rajouter autre chose?");
                    c=sc.next();
                    x=c.compareTo("oui");

                }
                System.out.println("Est ce que vous voulez afficher votre facture?");
                c=sc.next();
                x=c.compareTo("oui");
                //s.select_facture(w);
            }
        }



}
