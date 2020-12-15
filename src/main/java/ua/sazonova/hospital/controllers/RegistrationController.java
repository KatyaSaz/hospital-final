package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.DoctorService;
import ua.sazonova.hospital.service.PatientService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public RegistrationController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping
    public String showRegFormUser(@ModelAttribute("user") User user){
        return "reg/user";
    }

    @PostMapping
    public String getUserInfo(@ModelAttribute("user") User user,
                              Model model){

        if(user.getRole().equals(Role.DOCTOR)){
            doctorService.setUser(user);
            model.addAttribute("doctor", new Doctor());
            return "reg/doctor";

        }else if(user.getRole().equals(Role.PATIENT)){
            patientService.setUser(user);
            model.addAttribute("patient", new Patient());
            return "reg/patient";
        }
        return "redirect:/login";
    }

    @PostMapping("/doctor")
    public String getDoctorInfo(@ModelAttribute("doctor") Doctor doctor){
        doctorService.create(doctor);
        return "reg/success";
    }

    @PostMapping("/patient")
    public String getPatientInfo(@ModelAttribute("patient") Patient patient){
        patient.setDoctor(doctorService.getById(Doctor.DEFAULT_DOCTOR_ID));
        patientService.create(patient);
        return "reg/success";
    }
}
