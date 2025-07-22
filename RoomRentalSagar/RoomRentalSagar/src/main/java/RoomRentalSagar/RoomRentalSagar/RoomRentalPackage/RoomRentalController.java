package RoomRentalSagar.RoomRentalSagar.RoomOwnersPackage;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Optional;

@Controller
public class RoomOwnersController {
    @Autowired
    RoomOwnersRepository roomRentalRepository;

    @GetMapping("/signup")
    public String getSignUp(Model model){
        model.addAttribute("roomowner",new RoomOwners());
        return "Forms/signup";
    }

    @PostMapping("/register")
    public String getRegister(@ModelAttribute("roomrental") RoomOwners roomRental, Model model){

       if(roomRentalRepository.findByEmail(roomRental.getEmail()).isPresent()){
            model.addAttribute("alreadyexists","Email Already Exists");
            return "Forms/signup";
        }


        RoomOwners roomOwners=new RoomOwners();
        roomOwners.setFirstname(roomRental.getFirstname());
        roomOwners.setLastname(roomRental.getLastname());
        roomOwners.setEmail(roomRental.getEmail());
        roomOwners.setPassword(roomRental.getPassword());
        roomOwners.setPhonenumber(roomRental.getPhonenumber());
        roomOwners.setStatus('Y');
        roomOwners.setJoiningdate(new Date());
        roomOwners.setProfile_pic(null);
        roomOwners.setAddress(null);
        roomRentalRepository.save(roomOwners);
        model.addAttribute("after_register","Registration Successful");
        return "Forms/login";
    }

    @GetMapping("/dashboard")
    public String getDashboard(String email, String password, HttpSession session, Model model){
        Optional<RoomOwners> roomRental=roomRentalRepository.findByEmailAndPassword(email,password);
        System.out.println("Inside the Check User");
        if(roomRental.isPresent()){
            session.setAttribute("roomRental",roomRental.get());
            model.addAttribute("roomRental",roomRental.get());
            return "Forms/dashboard";
        }
        model.addAttribute("email",email);
        model.addAttribute("password",password);
        model.addAttribute("invalid","Invalid credential");
        return "Forms/login";
    }

   
}
