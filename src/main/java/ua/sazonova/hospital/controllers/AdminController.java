package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.service.DoctorService;
import ua.sazonova.hospital.service.PatientService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public AdminController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping()
   // @PreAuthorize("hasAuthority('admin:read')")
    public String startPage(){
        return "admin/index";
    }

    @GetMapping("/doctors")
    public String getAllDoctors(Model model){
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin/showDoctors";
    }

    @RequestMapping("/doctors/{id}")
    public String getDoctorById(@PathVariable("id") Long id, Model model){
        model.addAttribute("doctor", doctorService.getById(id));
        return "admin/oneDoctor";
    }

    @PostMapping("/delete/doctor/{id}")
    public String deleteDoctor(@PathVariable("id") Long id){
        doctorService.delete(doctorService.getById(id));
        return "redirect:/admin/doctors";
    }

    @PostMapping("/doctors/sort")
    public String sortDoctors (@RequestParam String field,
                               @RequestParam String direction,
                               Model model) {
        if (field.equals("") && direction.equals("")) {
            return "redirect:/admin/doctors";
        }else{
            model.addAttribute("doctors", doctorService.sortedList(field, direction));
            return "admin/showDoctors";
        }
    }

    @PostMapping("/doctors/search")
    public String sortDoctors (@RequestParam DoctorType type,
                               Model model) {
        if (type.equals("")) {
            return "redirect:/admin/doctors";
        }else{
            model.addAttribute("doctors", doctorService.getByOneType(type));
            return "admin/showDoctors";
        }
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model){
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin/showPatients";
    }

    @RequestMapping("/patients/{id}")
    public String getPatientById(@PathVariable("id") Long id, Model model){
        model.addAttribute("patient", patientService.getById(id));
        return "admin/onePatient";
    }

    @PostMapping("/delete/patient/{id}")
    public String deletePatient(@PathVariable("id") Long id){
        patientService.delete(patientService.getById(id));
        return "redirect:/admin/patients";
    }

    @PostMapping("/patients/sort")
    public String sortPatients (@RequestParam String field,
                              @RequestParam String direction,
                              Model model) {
        if (field.equals("") && direction.equals("")) {
            return "redirect:/admin/patients";
        }else{
            model.addAttribute("patients", patientService.sortedList(field, direction));
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "admin/showPatients";
        }
    }

    @PostMapping("/appoint/{id}")
    public String appointDoctorToUser (@PathVariable("id") Long id,
                                       @RequestParam Long docId) {
        if(docId!=0){
            Patient pat = patientService.getById(id);
            if(!pat.getDoctor().getId().equals(docId)){
                pat.setDoctor(doctorService.getById(docId));
                patientService.save(pat);
            }
        }
        System.out.println(docId);
        return "redirect:/admin/patients";
    }




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


}
