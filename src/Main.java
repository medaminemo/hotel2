
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;



    public class Main {
       public static Scanner sc ;
       public static SelectRecords s;
       public static vEng r;
       public static reservation res;
       public static Update u;
        public static boolean equal(String i) {
            int x = i.compareTo("yes");
            int y = i.compareTo("no");
            return x == 0 || y == 0;
        }




     public static void main(String[] args) throws ParseException, IOException {
          sc = new Scanner(System.in);
          s= new SelectRecords();
          r= new vEng();
          u=new Update();

          int f=0;
          int l=0;
         f=s.selectMax("id_client","client");
          String asciiArt = "  _   _   _   _   _\n / \\ / \\ / \\ / \\ / \\\n( H | O | T | E | L )\n \\_/ \\_/ \\_/ \\_/ \\_/\n";
          System.out.println(asciiArt);
          String i="";
         System.out.println("Select the language:");
         int x=0;
         do{
             System.out.println("1 for english\n2 en Francais ");
             x=sc.nextInt();

         }while (x!=1&&x!=2);


         while (!equal(i)){
             System.out.println("if you're a new client type yes or no if you're not a new client");
             i=sc.next();

         }
         int id=0;
         if(i.compareTo("yes")==0){
             System.out.println("x");
             id=r.newclient();
             System.out.println("We have successfully added you to our database");
         }
         System.out.println("--------------------------------------------------------------------------");
         if(id==0){
             System.out.println("Insert your id code:");
             id = sc.nextInt();
             while(id>f || id<1){
                 System.out.println("id non trouve,Insert your id code:");
                 id = sc.nextInt();
             }
             System.out.println("----->"+id);

             System.out.println("vous etes  :"+s.selectclient(id));
             System.out.println("Si c'est vous tapez 1 Sinon il faut vois inscrire dans notre base tapez 2 alors ");
             int t=sc.nextInt();
             int p=0;

             if(t!=1){
                 vEng v=new vEng();
                 p=v.newclient();
                 l=res.reserver(p);
             }
         }


         System.out.println("TYPE 1 IF YOU WANT TO BOOK IN OUR HOTEL \n     2 IF YOU WANT TO UPDATE YOUR PERSONAL DATA \n 3 IF YOU WANT TO UPDATE YOUR RESERVATION \n     4 IF YOU WANT TO DELETE YOUR DATA IN OUR DATABASE \n 5 CANCEL THE RESERVATION");
         int o=sc.nextInt();
         System.out.println(id);
         Update u =new Update();

         u.update_dispo();
         while (o<1||o>5){
             System.out.println("TYPE 1 IF YOU WANT TO BOOK IN OUR HOTEL \n     2 IF YOU WANT TO UPDATE YOUR PERSONAL DATA \n 3 IF YOU WANT TO UPDATE YOUR RESERVATION \n     4 IF YOU WANT TO DELETE YOUR DATA IN OUR DATABASE \n 5 CANCEL THE RESERVATION");
             o=sc.nextInt();
         }
         if (o==1){
             System.out.println(id);
             l=res.reserver(id);
             Prestation p=new Prestation();
             p.ajout_prestation(l);

         }
         else if(o==2){
             u.update_client(id);
         }else if (o==3){
            u.update_reservation();
         }else if (o==4){
            u.delete_client();
         }
         else{
            u.delete_reservation();
         }





      }
}