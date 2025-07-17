package RoomRentalSagar.RoomRentalSagar.MainWebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String getHomePage(){
        return "MainPage/index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "Forms/login";
    }

}
