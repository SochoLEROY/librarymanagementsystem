/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import library.management.system.model.Book;

/**
 *
 * @author ASUS
 */
public class Database {

    private static Connection conn;
    private static Database db;
    private Preferences prefs;

    private Database() throws SQLException {
        connect();
        createDatabase();
        createTables();

    }

    public static Database getInstance() throws SQLException {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    private void connect() throws SQLException {
        prefs = Preferences.userRoot().node("lbdb");

        String host = prefs.get("host", "local host");
        int port = prefs.getInt("port", 3306);
        String user = prefs.get("user", "root");
        String password = prefs.get("password", "");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", user, password);

    }

    public void createDatabase() {
        String createSql = "create database if not exists lmsdb character set utf8 collate utf8_general_ci";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createSql);
        } catch (SQLException ex) {
            System.out.println("Cannot create database");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void createTables() {
        String createBookTableSql = "create table if not exists lmsdb.books (id varchar(255) primary key,title varchar(255),author varchar(255),publisher varchar(255),is_available boolean default true)";
        String createMemberTableSql = "create table if not exists lmsdb.members (id varchar(255) primary key,name varchar(255),mobile varchar(255),address varchar(255))";
        String createIssueTableSql = "create table if not exists lmsdb.issue(book_id varchar(255),member_id varchar(255),issue_date Date,renew_count int,foreign key (book_id) references books(id),foreign key (member_id) references members(id))";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createBookTableSql);
        } catch (SQLException ex) {
            System.out.println("Cannot create database");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createMemberTableSql);
        } catch (SQLException ex) {
            System.out.println("Cannot create database");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createIssueTableSql);
        } catch (SQLException ex) {
            System.out.println("Cannot create database");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void disConnect() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public void saveBook(Book book) {

    }

    public Connection getConnection() {
        return conn;
    }

}
