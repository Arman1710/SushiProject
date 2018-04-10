package kz.sushi.dao.connectionPool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPool {
    private static Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    private static final String URL = resourceBundle.getString("url");
    private static final String USER = resourceBundle.getString("user");
    private static final String PASSWORD = resourceBundle.getString("password");
    private static final int MAX_CONN = Integer.parseInt(resourceBundle.getString("maxConn"));

    private static ConnectionPool instance;
    private List<Connection> freeConnections = new ArrayList<>(MAX_CONN);

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool() ;
        return instance;
    }

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            log.error(e);
        }
        while (freeConnections.size() < MAX_CONN) {
            freeConnections.add(newConnection());
        }
    }

    public synchronized Connection getConnection() {
        Connection con  = null;
        if (!freeConnections.isEmpty()) {
            con = freeConnections.get(freeConnections.size() -1);
            freeConnections.remove(con);
            try {
                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (Exception e) {
                con = getConnection();
                log.error(e);
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            log.error(e);
        }
        return con;
    }

    public void returnConnection (Connection connection){
        freeConnections.add(connection);
    }

}
