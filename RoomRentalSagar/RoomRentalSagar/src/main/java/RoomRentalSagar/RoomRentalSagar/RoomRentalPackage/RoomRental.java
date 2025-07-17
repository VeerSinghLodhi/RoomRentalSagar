package RoomRentalSagar.RoomRentalSagar.RoomRentalPackage;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "roomrental")
public class RoomRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roomrentalid;

    private String firstname,lastname,email,phonenumber,password;
    private char status;
    private Date joiningdate;

    public RoomRental() {
    }

    public RoomRental(int roomrentalid, String firstname, String lastname, String email, String phonenumber, String password, char status, Date date) {
        this.roomrentalid = roomrentalid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.status = status;
        this.joiningdate = date;
    }

    public int getRoomrentalid() {
        return roomrentalid;
    }

    public void setRoomrentalid(int roomrentalid) {
        this.roomrentalid = roomrentalid;
    }

    public String getFirstname() {
        return firstname;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Date joiningdate) {
        this.joiningdate = joiningdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
