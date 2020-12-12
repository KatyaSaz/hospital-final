package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sazonova.hospital.dao.PatientDAO;

@Controller
//@PreAuthorize("hasAuthority('PATIENT')")
@RequestMapping("/patient")
public class PatientController {

    private PatientDAO patientDAO;

    @Autowired
    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
       // currentPat = patientDAO.show(2);
    }

    @GetMapping("{id}")
    public String myProfile(@PathVariable("id") int id,
                            Model model){
        System.out.println("Hello "+id);
        model.addAttribute("patient", patientDAO.show(id));
        return "patient/info";
    }

//    @RequestMapping("/{id}")
//    public String getPatientById(@PathVariable("id") int id, Model model){
//        model.addAttribute("patient", patientDAO.show(id));
//        return "patient/info";
//    }
}
