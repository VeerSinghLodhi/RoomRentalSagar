package RoomRentalSagar.RoomRentalSagar.RoomRentalPackage;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class RoomRentalController {
    @Autowired
    RoomRentalRepository roomRentalRepository;

    @GetMapping("/signup")
    public String getSignUp(Model model){
        model.addAttribute("roomrental",new RoomRental());
        return "Forms/signup";
    }

    @PostMapping("/register")
    public String getRegister(@ModelAttribute("roomrental") RoomRental roomRental,Model model){

       if(roomRentalRepository.findByEmail(roomRental.getEmail()).isPresent()){
            model.addAttribute("alreadyexists","Email Already Exists");
            return "Forms/signup";
        }


        RoomRental newRoomRental=new RoomRental();
        newRoomRental.setFirstname(roomRental.getFirstname());
        newRoomRental.setLastname(roomRental.getLastname());
        newRoomRental.setEmail(roomRental.getEmail());
        newRoomRental.setPassword(roomRental.getPassword());
        newRoomRental.setPhonenumber(roomRental.getPhonenumber());
        newRoomRental.setStatus('Y');
        newRoomRental.setJoiningdate(new Date());
        roomRentalRepository.save(newRoomRental);
        model.addAttribute("after_register","Registration Successful");
        return "Forms/login";
    }

    @GetMapping("/checkuser")
    public String getDashboard(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        RoomRental roomRental=roomRentalRepository.findByEmailAndPassword(email,password);
        session.setAttribute("roomRental",roomRental);
        model.addAttribute("roomRental",roomRental);
        return "Forms/dashboard";
    }

    @GetMapping("/checkemail")
    public String getCheckEmail(String email){

        return "";
    }
}
