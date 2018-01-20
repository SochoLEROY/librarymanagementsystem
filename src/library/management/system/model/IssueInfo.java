/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class IssueInfo {
    private String memberId;
    private String bookId;
    private Date issueDate;
    private int renewCount;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }

    public IssueInfo(String memberId, String bookId, Date issueDate, int renewCount) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.renewCount = renewCount;
    }

}
