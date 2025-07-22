package RoomRentalSagar.RoomRentalSagar.RoomOwnersPackage;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "roomowners")
public class RoomOwners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String firstname,address,lastname,email,phone_num,password;
    private char status;
    private Date joiningdate;

    byte profile_pic[];

    public RoomOwners() {
    }

    public RoomOwners(int roomrentalid, String firstname, String lastname, String email, String phonenumber, String password, char status, Date date) {
        this.id = roomrentalid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone_num = phonenumber;
        this.password = password;
        this.status = status;
        this.joiningdate = date;
    }

    public int getRoomrentalid() {
        return id;
    }

    public void setRoomrentalid(int roomrentalid) {
        this.id = roomrentalid;
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
        return phone_num;
    }

    public void setPhonenumber(String phonenumber) {
        this.phone_num = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public byte []getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(byte [] profile_pic) {
        this.profile_pic = profile_pic;
    }
}
