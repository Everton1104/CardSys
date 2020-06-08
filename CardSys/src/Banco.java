import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Banco {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/cardsysdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    Connection conn = null;
    Statement stmt = null;
    
    public ArrayList<String> execute(String IDCard){
    	ArrayList<String> dados = new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet tipo = stmt.executeQuery("SELECT tipo FROM idcard WHERE cartao = "+IDCard+";");
            if (tipo.next()) {
            	if(tipo.getString("tipo")=="C") {
            		ResultSet cliente = stmt.executeQuery("SELECT nomeC FROM cliente WHERE cartao = "+IDCard+";");
            		
            	}
            }
        }
        catch (SQLException se) {se.printStackTrace();} 
        catch (Exception e) {e.printStackTrace();} 
        finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } 
            catch (SQLException se) {se.printStackTrace();}
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (SQLException se) {se.printStackTrace();}
        }
        return dados;
    }
}