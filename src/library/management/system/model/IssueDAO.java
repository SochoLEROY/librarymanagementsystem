/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.management.system.database.Database;

/**
 *
 * @author ASUS
 */
public class IssueDAO {
    public void saveIssueInfo(String bookID,String memberID) throws SQLException{
        Connection conn=Database.getInstance().getConnection();
        
        String insertSql="insert into lmsdb.issue(book_id,member_id,issue_date,renew_count)values(?,?,CURDATE(),?)";
        PreparedStatement stmt=conn.prepareStatement(insertSql);
        stmt.setString(1, bookID);
        stmt.setString(2, memberID);
        stmt.setInt(3, 0);
        stmt.execute();
    }

    public IssueInfo getIssueInfo(String bookID) throws SQLException {
                     Connection conn=Database.getInstance().getConnection();
       IssueInfo issueInfo=null;
        String selectSql="select * from lmsdb.issue where book_id=?";
        PreparedStatement stmt=conn.prepareStatement(selectSql);
        stmt.setString(1, bookID);
        ResultSet results=stmt.executeQuery();
        if(results.next()){
            String bookId=results.getString("book_id");
            String memberId=results.getString("member_id");
            Date issueDate=results.getDate("issue_date");
            int renewCount=results.getInt("renew_count");
            issueInfo=new IssueInfo(memberId,bookId,issueDate,renewCount);
        }
        return issueInfo;
    }
     public void deleteIssueInfo(String bookID) throws SQLException {
              Connection conn=Database.getInstance().getConnection();
        
        String deleteSql="delete from lmsdb.issue where book_id=?";
        PreparedStatement stmt=conn.prepareStatement(deleteSql);
        stmt.setString(1, bookID);
        stmt.execute();
    }

    public void updateIssueInfo(String bookID) throws SQLException {
                Connection conn=Database.getInstance().getConnection();
        
        String updateSql="update lmsdb.issue set issue_date=curdate(),renew_count=renew_count+1 where book_id=?";
        PreparedStatement stmt=conn.prepareStatement(updateSql);
        stmt.setString(1, bookID);
        stmt.execute();
    }
    
}
