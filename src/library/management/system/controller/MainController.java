/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.management.system.model.Book;
import library.management.system.model.BookDAO;
import library.management.system.model.IssueDAO;
import library.management.system.model.IssueInfo;
import library.management.system.model.Member;
import library.management.system.model.MemberDAO;

/**
 *
 * @author ASUS
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton newBookBtn;
    @FXML
    private MenuItem closeItem;
    @FXML
    private JFXButton newMemberBtn;
    @FXML
    private JFXButton booksBtn;
    @FXML
    private JFXButton membersBtn;
    @FXML
    private JFXTextField bookIDField;
    @FXML
    private Text titleText;
    @FXML
    private Text authorText;

    private boolean isBookInfoSearch = true;
    private boolean isMemberInfoSearch = true;

    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    private IssueDAO issueDAO;
    private Text publisherText;
    @FXML
    private JFXTextField memberIDField;

    @FXML
    private Text nameText;
    @FXML
    private Text mobileText;
    @FXML
    private Text addressText;
    @FXML
    private JFXButton issueBtn;
    @FXML
    private Text availableText;
    @FXML
    private JFXTextField issueBookIDField;
    @FXML
    private Text mNameText;
    @FXML
    private Text mMobileText;
    @FXML
    private Text mAddressText;
    @FXML
    private Text bTitleText;
    @FXML
    private Text bAuthorText;
    @FXML
    private Text bPublisherText;
    @FXML
    private Text issueDateText;
    @FXML
    private Text renewCountText;
    @FXML
    private JFXButton renewBtn;
    @FXML
    private JFXButton submissionBtn;
    @FXML
    private MenuItem serverItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
        issueDAO = new IssueDAO();
    }

    @FXML
    private void LoadNewBookBtnAction(ActionEvent event) throws IOException {
        loadWindow("New Book", "/library/management/system/view/newBook.fxml");

    }

    @FXML
    private void LoadNewBookMemberBtnAction(ActionEvent event) throws IOException {
        loadWindow("New Member", "/library/management/system/view/newMember.fxml");

    }

    @FXML
    private void LoadBookslistWindow(ActionEvent event) throws IOException {
        loadWindow("Book List", "/library/management/system/view/bookslist.fxml");

    }

    @FXML
    private void LoadMemberslistWindow(ActionEvent event) throws IOException {
        loadWindow("Member List", "/library/management/system/view/memberslist.fxml");

    }

    private void loadWindow(String title, String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(newMemberBtn.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.FINISH, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Confirm");
        alert.setContentText("Do you want to close");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.get() == ButtonType.YES) {

            Stage stage = (Stage) newBookBtn.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void searchBookInfo(ActionEvent event) {
        clearText();
        String bookID = bookIDField.getText();
        if (bookID.isEmpty()) {
            return;
        }

        try {
            Book book = bookDAO.getBook(bookID);
            if (book != null) {
                titleText.setText(book.getTitle());
                authorText.setText(book.getAuthor());
                boolean available = book.isAvailable();
                String availableStr = available ? "is available" : "not available";
                availableText.setText(availableStr);
                isBookInfoSearch = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearText() {
        titleText.setText("-");
        authorText.setText("-");
        availableText.setText("-");
    }

    @FXML
    private void searchMemberInfo(ActionEvent event) {

        clearData();
        String memberID = memberIDField.getText();
        if (memberID.isEmpty()) {
            return;
        }

        try {
            Member member = memberDAO.getMember(memberID);
            if (member != null) {

                nameText.setText(member.getName());
                mobileText.setText(member.getMobile());
                addressText.setText(member.getAddress());
                isMemberInfoSearch = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearData() {
        nameText.setText("-");
        mobileText.setText("-");

        addressText.setText("-");
    }

    @FXML
    private void saveIssueInfo(ActionEvent event) {
        String bookID = bookIDField.getText();
        String memberID = memberIDField.getText();
        if (bookID.isEmpty() || memberID.isEmpty()) {
            System.out.println("Plz search book and member info first");
            return;
        }
        if (!isBookInfoSearch && !isMemberInfoSearch) {
            System.out.println("Plz search book and member info first");
            return;
        }
        isBookInfoSearch = false;
        isMemberInfoSearch = false;

        if (availableText.getText().equals("not available")) {
            System.out.println("This book is already issue");
        }
        try {
            issueDAO.saveIssueInfo(bookID, memberID);
            bookDAO.updateBook(bookID, false);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchIssueInfo(ActionEvent event) {
        clearIssueInfo();

        String bookID = issueBookIDField.getText();

        try {
            IssueInfo issueInfo = issueDAO.getIssueInfo(bookID);
            if (issueInfo != null) {
                Book book = bookDAO.getBook(issueInfo.getBookId());
                bTitleText.setText(book.getTitle());
                bAuthorText.setText(book.getAuthor());
                bPublisherText.setText(book.getPublisher());

                Member member = memberDAO.getMember(issueInfo.getMemberId());
                mNameText.setText(member.getName());
                mMobileText.setText(member.getMobile());
                mAddressText.setText(member.getAddress());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY, u");
                String dateStr = dateFormat.format(issueInfo.getIssueDate());

                issueDateText.setText("Issue Date:" + dateStr);
                renewCountText.setText("Renew Count:" + issueInfo.getRenewCount());

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void startSubmission(ActionEvent event) {
        String bookID = issueBookIDField.getText();
        try {
            issueDAO.deleteIssueInfo(bookID);
            bookDAO.updateBook(bookID, true);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void renewBook(ActionEvent event) {
        String bookID = issueBookIDField.getText();
        try {
            issueDAO.updateIssueInfo(bookID);

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearIssueInfo() {
        mNameText.setText("-");
        mMobileText.setText("-");
        mAddressText.setText("-");
        bTitleText.setText("-");
        bAuthorText.setText("-");
        bPublisherText.setText("-");
        issueDateText.setText("-");
        renewCountText.setText("-");
    }

    @FXML
    private void loadDatabaseServerWindow(ActionEvent event) throws IOException {
        loadWindow("Database Server", "/library/management/system/view/server.fxml");
    }

}
