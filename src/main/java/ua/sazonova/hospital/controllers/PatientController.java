package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sazonova.hospital.service.PatientService;

@Controller
//@PreAuthorize("hasAuthority('PATIENT')")
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("{id}")
    public String myProfile(@PathVariable("id") Long id,
                            Model model){
        model.addAttribute("patient", patientService.getById(id));
        return "patient/info";
    }
}
