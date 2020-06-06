import java.sql.*;

public class Banco {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/cardsysdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public String bancoInit(String ID){
        Connection conn = null;
        Statement stmt = null;
        String tipo = "";
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            System.out.println("Connecting database...");
            conn = DriverManager.getConnection(
                    DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //Definicao do tipo de cartao.
            stmt = conn.createStatement();
            String sql = "SELECT tipo FROM idcard WHERE cartao = "+ID+";";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
	            tipo = res.getString("tipo");
            }
            if(tipo.contains("CLIENTE")) {
            	sql = "";
            }else {
            	sql = "";
            }
            res = stmt.executeQuery(sql);
        	if (res.next()) {
        		//recuperar dados
        	}
        } 
        catch (SQLException se) {se.printStackTrace();} 
        catch (Exception e) {e.printStackTrace();} 
        finally {
            try {
                if (stmt != null) {
                    conn.close();
                    System.out.println("Fechando conexao stmt.");
                }
            } 
            catch (SQLException se) {}
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Fechando conexao conn.");
                }
            } 
            catch (SQLException se) {se.printStackTrace();}
        }
        return ID;
    }
}