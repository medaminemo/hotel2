import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
    public Scanner sc;
    public static SelectRecords s;
    private Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3307/hotel";
            // create a connection to the database
            String username = "amine";
            String password = "amine";
            conn = DriverManager.getConnection(url, username, password);


            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void update_dispo() {
        s=new SelectRecords();
        String sql = "UPDATE Possede Join reservation using (num_chambre)   SET disponibilite = 0 WHERE (date_debut < DATE(NOW()) && date_fin >DATE(NOW()));";
        String sql1 = "UPDATE Possede Join reservation using (num_chambre)   SET disponibilite = 1 WHERE (date_debut > DATE(NOW()) || date_fin <DATE(NOW()));";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
             stmt.executeUpdate(sql);

            Connection conn1 = this.connect();
            Statement stmt1 = conn1.createStatement();
            stmt1.executeUpdate(sql1);
            // loop through the result set


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update_client(int id) {

        System.out.println("Que est ce que vous voulez modifier:\n 1 Votre adresse?\n2 Votre mail?\n3 Votre Numero");
        sc = new Scanner(System.in);
        int c = sc.nextInt();
        while (c < 1 || c > 3) {
            System.out.println("Que est ce que vous voulez modifier:\n 1 Votre adresse?\n2 Votre mail?\n3 Votre Numero");
            c = sc.nextInt();
        }
        String sql="";
        vEng v = new vEng();
        String adresse = null;
        if (c == 1) {
            adresse = "";
            String ville = "";
            String pays = "";
            String cp = "";
            System.out.println("what's the address ?");
            sc.nextLine();
            adresse = sc.nextLine();
            System.out.println(adresse);
            while (!v.verifier_adresse(adresse)) {
                System.out.println("sorry there's something wrong with the adress");
                adresse = sc.nextLine();
            }

            System.out.println("where does he live (name of the city)?");
            ville = sc.next();
            while (!v.verifier_prenom(ville)) {
                System.out.println("sorry there's something wrong with the name of the city");
                ville = sc.next();
            }

            System.out.println("where does he live(country)?");
            pays = sc.next();
            while (!v.verifier_prenom(pays)) {
                System.out.println("sorry there's something wrong with the name of the country");
                pays = sc.next();
            }

            System.out.println("what is the address code?");
            cp = sc.next();
            while (!v.verifier_cp(cp)) {
                System.out.println("sorry there's something wrong with the address code");
                cp = sc.next();
            }

             sql = "UPDATE client SET adresse_client='" + adresse + "',ville='" + ville + "',pays='"+pays+"',cp='"+cp+"' where id_client="+id+" );";
        }
        else if(c==2){
            System.out.println("LA NOUVELLE EMAIL:");
            String email=sc.next();
            while(!v.verifier_email(email)){
                System.out.println("sorry there's something wrong with the e-mail");
                email=sc.next();
            }
            sql="Update client set email='"+email+"' where id_client="+id+"";
        }
        else {
            System.out.println("VOTRE NOUVEAUX NUMERO?");
            String tel=sc.next();
            while(!v.verifier_tel(tel)){
                System.out.println("sorry there's something wrong with the phone number");
                tel=sc.next();
            }
            sql="update client set tel_client='"+tel+" where id_client="+id;
        }

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);


            // loop through the result set


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update_reservation()
    {

    }

    public static void delete_reservation()
    {
        s=new SelectRecords();
        
        System.out.println("Donnez le num de la reservation que vous voulez eliminer:");

        String sql = "DELETE FROM Reservation WHERE id_reservation = ?";

    }

    public static void delete_client()
    {

    }
    public static void main(String args[]){
        Update p=new Update();
        //p.update_client();
    }
}