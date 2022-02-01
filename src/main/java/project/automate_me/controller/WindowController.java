package project.automate_me.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.automate_me.model.CustomUserDetails;
import project.automate_me.model.SelfReviewResponse;
import project.automate_me.model.SmartWindow;
import project.automate_me.service.WindowService;

@RestController
public class WindowController {

    private final WindowService windowService;

    WindowController(WindowService windowService) {
        this.windowService = windowService;
    }


    @PostMapping("/addWindow")
    SmartWindow newWindow(@RequestBody SmartWindow newSmartWindow) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return windowService.addWindow(newSmartWindow, username);
    }
    @GetMapping("/checkSmokeGasLevels")
    SmartWindow windowStatusDanger(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return windowService.checkSmokeGasLevels(username);
    }
    @GetMapping("/checkRoomTemperature")
    SmartWindow windowStatusTemperature(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return windowService.checkRoomTemperature(username);
    }
    @GetMapping("/setWindowPosition/{state}/{angle}")
    SmartWindow setWindowPosition(@PathVariable String state,@PathVariable int angle){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return  windowService.setWindowPosition(username,state,angle);
    }
    @GetMapping("/setWindowImage/{image}")
    SmartWindow setWindowPosition(@PathVariable String image){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return  windowService.setWindowImage(username,image);
    }
    @GetMapping("/selfReview")
    SelfReviewResponse selfReview(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return windowService.selfReview(username);
    }
}
