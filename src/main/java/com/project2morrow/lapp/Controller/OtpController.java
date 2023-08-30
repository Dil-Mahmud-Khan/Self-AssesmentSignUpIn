package com.project2morrow.lapp.Controller;

import com.project2morrow.lapp.Otp.MyOTPgen;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class OtpController {

    @RequestMapping("/regsubmit")
    public String submitpage2(@RequestParam("email")String email, Model model) {
        String otp= MyOTPgen.generateOTP();
        //model.addAttribute("email",email);
        model.addAttribute("otpV","232");
        //	MyMail.send(email,otp);
        System.out.println("submit page 2 working");
        return "page3";
    }

    @PostMapping("/otpsubmit")
    public String otpSubmit(@RequestParam("otp")String otp, @RequestParam("genOtp")String genOtp, Model model) {
        System.out.println("Verification: "+(otp==genOtp));
        return "Perfect";

    }

}
