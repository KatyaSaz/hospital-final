//package ua.sazonova.hospital.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ua.sazonova.hospital.dao.DoctorDAO;
//import ua.sazonova.hospital.dao.PatientDAO;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private PatientDAO patientDAO;
//    private DoctorDAO doctorDAO;
//
//    @Autowired
//    public AdminController(PatientDAO patientDAO, DoctorDAO doctorDAO) {
//        this.patientDAO = patientDAO;
//        this.doctorDAO = doctorDAO;
//    }
//
//    @GetMapping()
//   // @PreAuthorize("hasAuthority('admin:read')")
//    public String startPage(){
//        return "admin/index";
//    }
//
//    @GetMapping("/doctors")
//    public String getAllDoctors(Model model){
//        model.addAttribute("doctors", doctorDAO.index());
//        return "admin/showDoctors";
//    }
//
//    @RequestMapping("/doctors/{id}")
//    public String getDoctorById(@PathVariable("id") int id, Model model){
//        model.addAttribute("doctor", doctorDAO.show(id));
//        return "admin/oneDoctor";
//    }
//
//    @GetMapping("/patients")
//    public String getAllPatients(Model model){
//        model.addAttribute("patients", patientDAO.index());
//        return "admin/showPatients";
//    }
//
//    @RequestMapping("/patients/{id}")
//    public String getPatientById(@PathVariable("id") int id, Model model){
//        model.addAttribute("patient", patientDAO.show(id));
//        return "admin/onePatient";
//    }
//
//    @GetMapping("/patient/new")
//    public String newPatient(@ModelAttribute("patient") Patient patient){
//        return "admin/newPatient";
//    }
//
//
//    @PostMapping("/pat")
//    public String createPatient(@ModelAttribute("patient")  Patient patient){
//        System.out.println(patient);
//        patientDAO.save(patient);
//        return "redirect:/admin/patients";
//    }
//
//    @GetMapping("/doctor/new")
//    public String newDoctor(@ModelAttribute("doctor") Doctor doctor){
//        return "admin/newDoctor";
//    }
//
//
//    @PostMapping("/doc")
//    public String createDoctor(@ModelAttribute("doctor")  Doctor doctor) {
//        doctorDAO.save(doctor);
//        return "redirect:/admin/doctors";
//    }
//
//    @DeleteMapping("/patients/{id}")
//    public String deletePatient(@PathVariable("id") int id){
//        System.out.println("in delete pat");
//        patientDAO.delete(id);
//        return "admin/patients";
//    }
//
//    @DeleteMapping("/doctors/{id}")
//    public String deleteDoctor(@PathVariable("id") int id){
//
//        System.out.println("in delete doctor");
//        System.out.println(doctorDAO.show(id).getName());
//        doctorDAO.delete(id);
//        return "admin/index";
//    }
//
//}
