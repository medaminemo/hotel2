    import java.sql.*;

    public class InsertRecords {

        public Connection connect() {
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
       public void insert_reservation(int id_r,int id_c,String dd,String df,int x){
            String sql="Insert into reservation(id_reservation,id_client,date_debut,date_fin,num_chambre) values("+id_r+","+id_c+",'"+dd+"','"+df+"',"+x+")";
           try{
               Connection conn = this.connect();
               PreparedStatement pstmt = conn.prepareStatement(sql);
               pstmt.execute(sql);

           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

        public void insert_facture(int x,String s,int p){
            String sql="Insert into facture (id_reservation,nom_prestation,nb_personnes) values("+x+",'"+s+"',"+p+")";
            try{
                Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute(sql);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        public void insert_client(String nom_client, String prenom_client,String adresse_client,String ville_client,String cp,String pays_client,String email_client,String tel_client, String mdp) {
            SelectRecords app = new SelectRecords();
            int id=app.selectMax("id_client","client")+1;


            String sql = "INSERT INTO client(id_client,nom_client,prenom_client,adresse_client,ville_client,cp_client,pays_client,email_client,tel_client,password) VALUES ('"+id+"','"+nom_client+"','"+prenom_client+"','"+adresse_client+"','"+ville_client+"','"+cp+"','"+pays_client+"','"+email_client+"','"+tel_client+"','"+mdp+"');";
            try{
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute(sql);

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
       
        public static void main(String[] args) {  
       
            InsertRecords app = new InsertRecords();  
            //app.insert_facture(4,"SPA");

        }
       
    }  