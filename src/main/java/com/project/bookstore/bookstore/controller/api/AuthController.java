package com.project.bookstore.bookstore.controller.api;


import com.project.bookstore.bookstore.model.entity.AppUser;
import com.project.bookstore.bookstore.registration.RegistrationRequest;
import com.project.bookstore.bookstore.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor

public class AuthController {

    private final RegistrationService registrationService;

    @GetMapping()
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        AppUser user = new AppUser();
        model.addAttribute("user", user);
        return "register";
    }

//    @PostMapping("/register/save")
//    public String registration(@Validated @ModelAttribute("user") AppUser userDto,
//                               BindingResult result,
//                               Model model){
//        AppUser existingUser = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("user", userDto);
//            return "/register";
//        }
//
//        userService.saveUser(userDto);
//        return "redirect:/register?success";
//    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping("/register/confirm")
    public String confirm( @RequestParam("token") String token,Model model){
         registrationService.confirmedToken(token);
        return "redirect:/login?success";
    }
}
