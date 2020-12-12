package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.service.DoctorService;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
//    private DoctorDAO doctorDAO;
//
  //  private Patient currentPat;

    private DoctorService doctorService;
    private Doctor currentDoc;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping()
    public String showStartPage(){
        return "doctor/index";
    }

    @GetMapping("/{id}")
    public String startPage(@PathVariable("id") Long id){
        currentDoc = doctorService.getById(id);
        return "doctor/index";
    }


    @GetMapping("/info")
    public String myProfile(Model model){
        model.addAttribute("doctor", currentDoc);
        return "doctor/infoAbout";
    }

    @GetMapping("/patients")
    public String showAllMyPatients(Model model){
        model.addAttribute("patients", currentDoc.getPatients());
        return "doctor/showMyPatients";
    }

//    @RequestMapping("/patients/{id}")
//    public String getPatientById(@PathVariable("id") int id, Model model){
//        //currentPat = doctorDAO.showPat(currentDoc, id);
//        model.addAttribute("patient", doctorDAO.showPat(currentDoc, id));
//        return "doctor/onePatient";
//    }
//
//    @RequestMapping("/patients/{id}/write")
//    public String showFormForWrite(@PathVariable("id") int id,
//                                   Model model){
//        CardRecord record = new CardRecord();
//        record.setDoctorName(currentDoc.getName());
//        model.addAttribute("record", record);
//        model.addAttribute("ID", id);
//        return "doctor/writeRecord";
//    }
//
//
//   // @PostMapping
//    @PostMapping("/patients/{id}/record")
//    public String createDoctor(@PathVariable("id") int id,
//                               @ModelAttribute("record")  CardRecord cardRecord) {
//        Patient getPat = doctorDAO.showPat(currentDoc, id);
//        if(getPat!=null){
//            System.out.println(getPat.getName());
//            System.out.println(cardRecord.getDescription());
//            List<CardRecord> cr = getPat.getRecords();
//            System.out.println("size "+cr.size());
//            cr.add(cardRecord);
//        }else{
//            System.out.println(id);
//
//        }
//
//       // cardRecord.setDoctorName(currentDoc.getName());
//        //currentPat.getRecords().add(cardRecord);
//       // currentPat=null;
//        return "redirect:/doctor/patients/"+id;
//    }

//
//    @RequestMapping("/patients/{id}/write")
//    public String showFormForWrite(@PathVariable("id") int id, Model model){
//        model.addAttribute("patient", doctorDAO.showPat(currentDoc, id));
//        model.addAttribute("patients", currentDoc.getPatients());
//        return "doctor/showMyPatients";
//    }

//    @PostMapping()
//    public String createDoctor(@ModelAttribute("")  ) {
//        doctorDAO.save(doctor);
//        return "redirect:/admin/doctors";
//    }
}
