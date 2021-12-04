package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libraryId;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
