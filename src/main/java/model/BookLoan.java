package model;

import javax.persistence.*;

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookLoanId;
    private String issueDate;
    private String returnDate;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "libraryId")
    private Library library;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public String toString() {
        return "BookLoan{" +
                "bookLoanId=" + bookLoanId +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", book=" + book +
                ", library=" + library +
                ", user=" + user +
                '}';
    }
}
