/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mysql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author nicolastorres
 */
public class MySQL {

    private Connection conn = null;

    public MySQL() {
        HashMap conf = this.readConf();
        try {
            Class.forName(conf.get("adapter").toString());
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + conf.get("host") + ":" + conf.get("port") + "/" + conf.get("database"),
                    conf.get("user").toString(),
                    conf.get("password").toString()
            );

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public List getValues(String query) {
        List<Map<String, Object>> result = null;
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            result = this.processResult(rs);
        } catch (SQLException e) {
            System.out.println("SQLError: " + e);
        }

        return result;
    }

    private List processResult(ResultSet rs) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {

            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i < rsmd.getColumnCount(); ++i) {
                    map.put(rsmd.getColumnName(i), rs.getString(i));
                }

                result.add(map);
            }
        } catch (SQLException e) {
            System.out.println("SQLError: " + e);
        }

        return result;
    }

    private HashMap readConf() {
        HashMap<String, String> database = new HashMap();

        try {
            File fXmlFile = new File("src/library/mysql/database.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            database.put("adapter",
                    doc.getElementsByTagName("adapter").item(0).getTextContent()
            );
            database.put("host",
                    doc.getElementsByTagName("host").item(0).getTextContent()
            );
            database.put("port",
                    doc.getElementsByTagName("port").item(0).getTextContent()
            );
            database.put("user",
                    doc.getElementsByTagName("user").item(0).getTextContent()
            );
            database.put("password",
                    doc.getElementsByTagName("password").item(0).getTextContent()
            );
            database.put("database",
                    doc.getElementsByTagName("database").item(0).getTextContent()
            );
        } catch (Exception e) {
            System.out.println(e);
        }

        return database;
    }
}
