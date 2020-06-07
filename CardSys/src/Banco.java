import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/cardsysdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    Connection conn = null;
    Statement stmt = null;
    String tipo = "";
    
    public ArrayList<String> execute(String SQL){
    	ArrayList<String> dados = new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(SQL);
            if (res.next()) {
              dados.add(res.getString("tipo"));
              dados.add(res.getString("nomeP"));
              dados.add(res.getString("cartao"));
          }
            
//            stmt = conn.createStatement();
//            String sql = "SELECT tipo FROM idcard WHERE cartao = "+ID+";";
//            ResultSet res = stmt.executeQuery(sql);
//            if (res.next()) {
//                tipo = res.getString("tipo");
//            }
//            if(tipo.contains("C")) {
//            	ID = "cliente";
//            }else {
//            	ID = "produto";
//            }
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
    public String getTipo(String ID) {
    	return ""://terminar aki.
    }
    
    public ArrayList<String> getDados(String ID) {//retornar listas de dados
    	ArrayList<String> dados = new ArrayList<String>();
    	
    	return dados;
    }
    public String setDados(String ID) {
    	return "";
    }
}