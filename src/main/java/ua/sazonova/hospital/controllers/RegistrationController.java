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
import ua.sazonova.hospital.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public RegistrationController(UserService userService, DoctorService doctorService, PatientService patientService) {
        this.userService = userService;
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
        System.out.println(user.getEmail()+" "+user.getPassword()+" "+user.getRole());
        //userService.save(user);
        if(user.getRole().equals(Role.DOCTOR)){
            Doctor doctor = new Doctor();
            doctor.setUser(user);
            model.addAttribute("doctor", doctor);
            return "reg/doctor";

        }else if(user.getRole().equals(Role.PATIENT)){
            Patient patient = new Patient();
            patient.setUser(user);
            model.addAttribute("patient", patient);
            return "reg/patient";
        }
        return "redirect:/login";
    }

    @PostMapping("/doctor")
    public String getDoctorInfo(@ModelAttribute("doctor") Doctor doctor){
        System.out.println("doc:"
                +doctor.getName()+" "
                +doctor.getSurname()+" "
                +doctor.getType()+" "+
                +doctor.getExperience()+""
                +doctor.getUser().getEmail()+"");
        //doctorService.create(doctor);
        return "reg/success";
    }

    @PostMapping("/patient")
    public String getPatientInfo(@ModelAttribute("patient") Patient patient){
        System.out.println("pat:"
                +patient.getName()+" "
                +patient.getSurname()+" "
                +patient.getPhone()+" "+
                +patient.getYear()+""
                +patient.getUser().getEmail()+"");
        //patientService.create(patient);
        return "reg/success";
    }
}
