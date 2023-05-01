    import java.awt.*;

    import org.apache.pdfbox.pdmodel.font.PDFont;
    import org.apache.pdfbox.pdmodel.font.PDType1Font;

    import java.io.File;
    import java.sql.DriverManager;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.io.IOException;
    import java.text.ParseException;
    import java.time.LocalDate;
    import java.time.temporal.ChronoUnit;

    import org.apache.pdfbox.pdmodel.PDDocument;
    import org.apache.pdfbox.pdmodel.PDPage;
    import org.apache.pdfbox.pdmodel.PDPageContentStream;
    import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

    import javax.imageio.ImageIO;

    public class SelectRecords {
       
        private Connection connect() {
            Connection conn = null;
            try {
                // db parameters
                String url = "jdbc:mysql://localhost:3306/hotel";
                // create a connection to the database
                String username = "root";
                String password = "";
                conn = DriverManager.getConnection(url, username, password);


                System.out.println("Connection to SQLite has been established.");
                return conn;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }
      


        public int selectMax(String nom_colonne,String nom_table){
            String sql="SELECT max("+nom_colonne+") from "+nom_table+";";

            int max=-1;
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                // loop through the result set
                if (rs.next()) {
                    max = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return max;
        }
        public int selectNumChambre(int x){
            String sql="SELECT count(id_hotel) from possede where id_hotel="+x;

            int max=-1;
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                // loop through the result set
                if (rs.next()) {
                    max = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return max;
        }

        public String select_prestation(){
            String sql="SELECT nom_prestation from prestation;";
            String pre="";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
                int i=1;
                // loop through the result set
                while(rs.next()){
                    pre += (i + "  " + rs.getString(1) + "\n");
                    i++;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return pre;
        }
        public String selectclient(int id){
            String sql="SELECT nom_client,prenom_client from client where id_client='"+id+"';";

            String client="";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                // loop through the result set
                //if (rs.next()) {
                  //  max = rs.getInt(1);
                //}
                while(rs.next()){
                    client=client+" "+rs.getString("nom_client")+" "+rs.getString("prenom_client");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return client;
        }
        public String selecthotel(){
            String sql ="Select id_hotel,nom_hotel from hotel";
            String hotel="";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);


                while(rs.next()){
                    hotel=hotel+" "+rs.getString("id_hotel")+" "+rs.getString("nom_hotel")+System.lineSeparator();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return hotel;
        }

        public String selectchambre(int x){
            String sql ="Select num_chambre,capacite from possede join chambre using (num_chambre) where id_hotel="+x+" and disponibilite=1;";
            String hotel="";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                // loop through the result set
                //if (rs.next()) {
                //  max = rs.getInt(1);
                //}
                System.out.println("Les chambre disponible dans l'hotel selectionne sont :");
                while(rs.next()){
                    hotel=hotel+" numero "+rs.getString("num_chambre")+" qui peut contenir "+rs.getString("capacite")+" personnnes"+System.lineSeparator();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return hotel;
        }
        public String select_mdp(int id) throws IOException {
            String sql="select mdp from client where id_client="+id;
            String mdp="";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                // loop through the result set
                //if (rs.next()) {
                //  max = rs.getInt(1);
                //}
                if(rs.next()){
                    mdp=rs.getString("mdp");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return mdp;
        }
        public static int nbjour(String date1, String date2){
            LocalDate localDate1 = LocalDate.parse(date1);
            LocalDate localDate2 = LocalDate.parse(date2);
            return (int) ChronoUnit.DAYS.between(localDate1, localDate2);
        }
        public void select_facture(int w) throws IOException {
            File file = new File("C:\\Users\\Amine\\OneDrive\\Immagini\\b&b.jpg");
            String sql = "Select nom_client,prenom_client,adresse_client,ville_client,cp_client,num_chambre,prix_chambre,nom_hotel,date_debut,date_fin from reservation join client using (id_client) join possede using (num_chambre) join chambre using (num_chambre) join type_chambre using (capacite) join hotel using (id_hotel) where id_reservation=" + w + " ; ";
            String nom = "";
            String prenom = "";
            int numch = 0;
            String nomh = "";
            String dated = "";
            String datef = "";
            String prestation = "";
            String datefact = "";
            String adresse = "";
            String ville = "";
            String cp = "";
            String prix = "";
            String nb_personnes="";
            String tarif="";

            String sql1 = "select nom_prestation,date_facture,nb_personnes,tarif_prestation from facture join prestation using (nom_prestation) where id_reservation=" + w + ";";
            try {
                Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                Connection conn1 = this.connect();
                Statement stmt1 = conn1.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                // loop through the result set
                //if (rs.next()) {
                //  max = rs.getInt(1);
                //}
                while (rs1.next()) {
                    prestation = prestation + rs1.getString("nom_prestation") + "                 "+rs1.getString("nb_personnes")+"                          "+rs1.getString("tarif_prestation")+"\n";
                    datefact = rs1.getString("date_facture");

                }
                System.out.println(tarif);

                while (rs.next()) {

                    nom = rs.getString("nom_client");
                    prenom = rs.getString("prenom_client");
                    nomh = rs.getString("nom_hotel");
                    numch = rs.getInt("num_chambre");
                    dated = rs.getString("date_debut");
                    datef = rs.getString("date_fin");
                    adresse = rs.getString("adresse_client");
                    ville = rs.getString("ville_client");
                    cp = rs.getString("cp_client");
                    prix = rs.getString("prix_chambre");
                }
                System.out.println(nomh);
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }

            int nbjour = nbjour(dated, datef);

            System.out.println("Facture de M/Mme: " + nom + " " + prenom + "                      Date de Facture: " + datefact);
            System.out.println("Resident a :" + adresse + " " + ville + "," + cp);
            System.out.println("                                                                                     ");
            System.out.println("Nb de jours             Chambre reserve                      Prix par jour");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println(nbjour + "                       N " + numch + " au " + nomh + "                   " + prix);
            System.out.println("                                                                                     ");
            System.out.println("LES PRESTATION:");
            System.out.println("Type Prestation         Quantite                   Prix");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println(prestation);



        }

        public boolean verifier_dispo(String date1, String date2, int num_chambre, int num_hotel){
            reservation r=new reservation();
            boolean res=false;
            String sql="Select date_debut,date_fin from reservation join possede using (num_chambre) where num_chambre="+num_chambre+" and id_hotel="+num_hotel+" and disponibilite=1";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
                String date_debut="";
                String date_fin="";
                if (rs.next()) {
                    date_debut = rs.getString("date_debut");
                    date_fin = rs.getString("date_fin");
                    boolean valid = r.estapres(date1, date_fin) || r.estapres(date_debut, date2);
                    while (rs.next() && !valid) {
                        date_debut = rs.getString("date_debut");
                        date_fin = rs.getString("date_fin");
                        valid = r.estapres(date1,date_fin) || r.estapres(date_debut, date2);
                    }
                    res = !rs.next() && valid;
                } else {
                    res = true; // aucun conflit d'horaire
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return res ;
        }

        public static void main(String[] args) throws IOException {
            SelectRecords app = new SelectRecords();
            System.out.println(app.verifier_dispo("2023-04-20","2023-05-22",5,1));
        }
       
    }  