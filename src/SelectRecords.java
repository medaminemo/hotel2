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
        public void select_facture(int w) throws IOException {
            File file = new File("C:\\Users\\Amine\\OneDrive\\Immagini\\b&b.jpg");
            String sql="Select nom_client,prenom_client,num_chambre,nom_hotel,date_debut,date_fin from reservation join client using (id_client) join possede using (num_chambre) join hotel using (id_hotel) where id_reservation="+w+" ; ";
            String nom="";
            String prenom="";
            int numch=0;
            String nomh ="";
            String dated="";
            String datef="";
            String prestation="";

            String sql1="select nom_prestation from facture where id_reservation="+w+";";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);

                Connection conn1 = this.connect();
                Statement stmt1  = conn1.createStatement();
                ResultSet rs1    = stmt1.executeQuery(sql1);
                // loop through the result set
                //if (rs.next()) {
                //  max = rs.getInt(1);
                //}
                while (rs1.next()){
                    prestation=prestation+rs1.getString("nom_prestation")+"\n";
                }
                System.out.println(prestation);

                while(rs.next()){

                    nom=rs.getString("nom_client");
                    prenom=rs.getString("prenom_client");
                    nomh=rs.getString("nom_hotel");
                    numch=rs.getInt("num_chambre");
                    dated=rs.getString("date_debut");
                    datef=rs.getString("date_fin");

                }
                    System.out.println(nomh);
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            try {
                // Create a document object
                PDDocument doc = new PDDocument();

                // Create a page object
                PDPage page = new PDPage();

                // Add the page to the document
                doc.addPage(page);

                // Create an image object from the loaded image
                PDImageXObject img = PDImageXObject.createFromFileByContent(file, doc);

                // Create a content stream for the page
                PDPageContentStream contentStream = new PDPageContentStream(doc, page);

                // Draw the image on the page at position (0, 0) with width and height of the image
                PDFont font = PDType1Font.TIMES_BOLD;
                contentStream.setLeading(30);
                contentStream.drawImage(img, page.getMediaBox().getWidth() - 100, page.getMediaBox().getHeight() - 100, 100, 100);
                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.setNonStrokingColor(Color.black);
                contentStream.newLineAtOffset(50, 700); // Adjust the offset to position the text as desired

                // Add the title "Facture"
                contentStream.showText("Facture de Mr/Mme "+nom+" "+prenom);

                contentStream.newLineAtOffset(0, -30);

                contentStream.showText("Chambre reserve: "+numch+" dans l'hotel: "+nomh);
                contentStream.newLineAtOffset(0, -30);

                contentStream.showText("Pour une periode du "+dated+" au "+datef);
                contentStream.newLineAtOffset(0, -30);

                contentStream.showText("Prestation Reservee:"+prestation);
                contentStream.endText();

                // Close the content stream
                contentStream.close();

                // Save the document to a file
                doc.save("C:\\Users\\Amine\\OneDrive\\Documents\\hotel2\\test.pdf");

                // Close the document
                doc.close();

                System.out.println("PDF created successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) throws IOException {
            SelectRecords app = new SelectRecords();
            app.select_facture(6);

        }
       
    }  