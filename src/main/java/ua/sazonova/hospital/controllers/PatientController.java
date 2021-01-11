package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.service.CardRecordService;
import ua.sazonova.hospital.service.PatientService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    private CardRecordService cardRecordService;

    @Autowired
    public PatientController(PatientService patientService, CardRecordService cardRecordService) {
        this.patientService = patientService;
        this.cardRecordService = cardRecordService;
    }

    @GetMapping("{id}")
    public String myProfile(@PathVariable("id") Long id,
                            Model model) {
        model.addAttribute("patient", patientService.getById(id));
        return "patient/info";
    }

    @PostMapping("/download/{id}")
    public void downloadCard(@PathVariable("id") Long id,
                             HttpServletResponse response) {
        CardRecord cr = cardRecordService.getByID(id);
        response.setContentType("text");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + cr.getFileName());
        try (BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = cr.diagnoseToString().getBytes();
            outStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
