package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.CardRecordService;
import ua.sazonova.hospital.service.DoctorService;
import ua.sazonova.hospital.service.PatientService;

import java.util.List;


@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private PatientService patientService;
    private CardRecordService cardRecordService;
    private Doctor currentDoc;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientService patientService, CardRecordService cardRecordService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.cardRecordService = cardRecordService;
    }

    //    @GetMapping()
//    public String showStartPage(){
//        return "doctor/index";
//    }

    @GetMapping("/{id}")
    public String startPage(@PathVariable("id") Long id,
                            Model model){
        currentDoc = doctorService.getById(id);
        for(Patient p:currentDoc.getPatients()){
            System.out.println(p.getName()+" "+p.getId());
        }
        model.addAttribute("doctor", currentDoc);
        return "doctor/index";
    }

    @GetMapping("/patients")
    public String showAllMyPatients(Model model){
        model.addAttribute("patients", currentDoc.getPatients());
        return "doctor/showMyPatients";
    }

    @RequestMapping("/patients/{id}")
    public String getPatientById(@PathVariable("id") Long id, Model model){
        model.addAttribute("patient", patientService.getById(id));
        return "doctor/onePatient";
    }
    @RequestMapping("/patients/{id}/write")
    public String showFormForWrite(@PathVariable("id") int id,
                                   Model model){
        model.addAttribute("record", new CardRecord());
        model.addAttribute("ID", id);
        return "doctor/writeRecord";
    }

    @PostMapping("/patients/{id}/record")
    public String createDoctor(@PathVariable("id") Long id,
                               @ModelAttribute("record")  CardRecord cardRecord) {
        cardRecord.setPatient(patientService.getById(id));
        cardRecordService.save(cardRecord);
        return "redirect:/doctor/patients/"+id;
    }
}
