package RoomRentalSagar.RoomRentalSagar.RoomOwnersPackage;

import RoomRentalSagar.RoomRentalSagar.EmailServices.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Controller
public class RoomOwnersController {
    @Autowired
    RoomOwnersRepository roomRentalRepository;

    @Autowired
    EmailService emailService;

    @GetMapping("/signup")
    public String getSignUp(Model model){
        model.addAttribute("roomowner",new RoomOwners());
        return "Forms/signup";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "Forms/login";
    }

    @PostMapping("/register")
    public String getRegister(HttpSession session, Model model,@RequestParam("userotp") String userOTP){

        RoomOwners roomOwnersData=(RoomOwners) session.getAttribute("roomowner");
        String systemOTP = (String) session.getAttribute("systemotp");
        LocalTime tenMinutesLater = (LocalTime) session.getAttribute("otptime");// Added plus 10m
        LocalTime currentTime = LocalTime.now();

        // If OTPs didn't match
        if (!systemOTP.equals(userOTP)) {
            model.addAttribute("error", "Invalid OTP");
            return "Forms/OTPVerify";
        }

        //If OTP time expired
        if (currentTime.isAfter((tenMinutesLater))) {
            model.addAttribute("error", "OTP expired. Request a new one.");
            return "Forms/OTPVerify";
        }


        RoomOwners roomOwners=new RoomOwners();
        roomOwners.setFirstname(roomOwnersData.getFirstname());
        roomOwners.setLastname(roomOwnersData.getLastname());
        roomOwners.setEmail(roomOwnersData.getEmail());
        roomOwners.setPassword(roomOwnersData.getPassword());
        roomOwners.setPhonenumber(roomOwnersData.getPhonenumber());
        roomOwners.setStatus('Y');
        roomOwners.setJoiningdate(new Date());
        roomOwners.setProfile_pic(null);
        roomOwners.setAddress(null);
        roomRentalRepository.save(roomOwners);
        model.addAttribute("after_register","Registration Successful");
        session.invalidate();
        return "Forms/login";
//        return "redirect:/login"; Can Call direct mapping
    }

    @PostMapping("/verify")
    public String getVerifyByOtp(@ModelAttribute("roomowner") RoomOwners roomOwners, HttpSession session,Model model){

        if(roomRentalRepository.findByEmail(roomOwners.getEmail()).isPresent()){
            model.addAttribute("alreadyexists","Email Already Exists");
            return "Forms/signup";
        }

        String otp = emailService.sendOTP(roomOwners.getEmail());
        LocalTime currentTime = LocalTime.now();
        System.out.println("OTP sent time " + currentTime);
        // Add 10 minutes
        LocalTime tenMinutesLater = currentTime.plusMinutes(10);

        session.setAttribute("roomowner", roomOwners);
        session.setAttribute("systemotp", otp);
        session.setAttribute("otptime", tenMinutesLater);  // +10m

        return "Forms/OTPVerify";
    }

    @GetMapping("/senddashboard")
    public String dashboard(HttpSession session,Model model) {
        RoomOwners roomOwners=(RoomOwners)session.getAttribute("roomowner");
        session.setAttribute("roomowner",roomOwners);
        model.addAttribute("roomowner",roomOwners);
        return "Forms/dashboard";
    }

    @GetMapping("/dashboard")
    public String getDashboard(String email, String password, HttpSession session, Model model){
        Optional<RoomOwners> roomRental=roomRentalRepository.findByEmailAndPassword(email,password);
        System.out.println("Inside the Check User");
        if(roomRental.isPresent()){
            session.setAttribute("roomowner",roomRental.get());
            model.addAttribute("roomowner",roomRental.get());
            return "redirect:/senddashboard";
        }
        model.addAttribute("email",email);
        model.addAttribute("password",password);
        model.addAttribute("invalid","Invalid credential");
        return "Forms/login";
    }

    @PostMapping("/updatepass")
    public String getUpdatePassword(String oldpassword, String newpassword, Model model, HttpSession session) {

        RoomOwners roomOwners = (RoomOwners) session.getAttribute("roomowner");

        // Null check
        if (roomOwners == null) {
            model.addAttribute("sessionExpired", true);
            return "redirect:/login";  // or show a proper error page/message
        }

        Optional<RoomOwners> checkUser = roomRentalRepository.findByEmailAndPassword(roomOwners.getEmail(), oldpassword);
        System.out.println("Before if Condition");

        session.setAttribute("roomowner",roomOwners);
        model.addAttribute("roomowner",roomOwners);

        if (!checkUser.isPresent()) {
            model.addAttribute("oldpasswordisnotcorrect", true);
            System.out.println("Inside the if means no password match");
            return "Forms/dashboard";
        }

        RoomOwners owner = checkUser.get();
        owner.setPassword(newpassword);
        roomRentalRepository.save(owner);

        session.setAttribute("roomowner", owner);
        model.addAttribute("roomowner", owner);
        model.addAttribute("passwordUpdated", true);
        System.out.println("Password Updated");

        return "Forms/dashboard";
    }
}
