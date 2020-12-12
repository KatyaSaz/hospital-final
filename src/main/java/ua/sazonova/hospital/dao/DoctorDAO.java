package ua.sazonova.hospital.dao;

import org.springframework.stereotype.Component;
import ua.sazonova.hospital.models.doctor.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.models.patient.Patient;
import ua.sazonova.hospital.models.patient.card.CardRecord;
import ua.sazonova.hospital.entity.enam.Gender;
import ua.sazonova.hospital.models.patient.card.RecordType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DoctorDAO {

    private static int DOCTOR_COUNT;
    private static int PATIENT_COUNT;
    private List<Doctor> doctors;
    private List<Patient> patients;

    {
        patients = new ArrayList<>();
        List<CardRecord> list2 = new ArrayList<>();
        list2.add(
                new CardRecord(
                        "Lili",
                        "21.11.2020",
                        "Directed to massage",
                        RecordType.Direction));
        list2.add(
                new CardRecord(
                        "Pavel",
                        "23.11.2020",
                        "Make massage",
                        RecordType.Execution));

        list2.add(
                new CardRecord(
                        "Lili",
                        "23.11.2020",
                        "Rheumatism",
                        RecordType.Diagnosis));
        patients.add(new Patient(
                201,
                "Katya",
                "Sazonova",
                Gender.female,
                "07.12.1999",
                "+380972999090",
                list2));

        List<CardRecord> list1 = new ArrayList<>();
        list1.add(
                new CardRecord(
                        "Mike",
                        "04.12.2020",
                        "Referral for general blood test",
                        RecordType.Direction));
        list1.add(
                new CardRecord(
                        "Paula",
                        "05.12.2020",
                        "Take blood test",
                        RecordType.Execution));

        list1.add(
                new CardRecord(
                        "Mike",
                        "05.12.2020",
                        "Anemia",
                        RecordType.Diagnosis));

        patients.add(new Patient(
                202,
                "Xie",
                "Lian",
                Gender.male,
                "12.03.1945",
                "+380974569090",
                list1));


        List<CardRecord> list = new ArrayList<>();
        list.add(
                new CardRecord(
                        "Lili",
                        "05.12.2020",
                        "Directed to the optometrist",
                        RecordType.Direction));
        list.add(
                new CardRecord(
                        "Peter",
                        "06.12.2020",
                        "Retinal surgery",
                        RecordType.Execution));

        list.add(
                new CardRecord(
                        "Peter",
                        "06.12.2020",
                        "Operation completed without problems recovery time 2 weeks",
                        RecordType.Diagnosis));

        patients.add(new Patient(
               203,
                "Hua",
                "Cheng",
                Gender.male,
                "16.10.1950",
                "+380638053060",
                list));

        patients.add(new Patient(
                204,
                "Luo",
                "Binghe",
                Gender.male,
                "06.06.1996",
                "+380501653300",
                new ArrayList<>()
        ));
        patients.add(new Patient(
                205,
                "Vika",
                "Polushina",
                Gender.female,
                "07.11.2000",
                "+380972280628",
                new ArrayList<>()
        ));

        doctors=new ArrayList<>();
        doctors.add(new Doctor(
                101,
                        "Elisa",
                        "Hamilton",
                        DoctorType.optometrist,
                        25,
                        Arrays.asList(patients.get(2), patients.get(1))));
        doctors.add(new Doctor(
                102,
                "Mike",
                "Spaice",
                DoctorType.pediatrician,
                12,
                patients));
        doctors.add(new Doctor(
                103,
                "Mu",
                "Chifan",
                DoctorType.dermatologist,
                5,
                Arrays.asList(patients.get(1), patients.get(3), patients.get(4))));
        doctors.add(new Doctor(
                104,
                "Yulia",
                "Kotelyanets",
                DoctorType.surgeon,
                15,
                new ArrayList<>()));

    }

    public List<Doctor> index() {
        return doctors;
    }

    public Doctor show(int id) {
        return doctors.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public Patient showPat(Doctor doc, int id) {
        return doc.getPatients().stream().filter(pat -> pat.getId() == id).findAny().orElse(null);
    }

    public void save(Doctor doctor){
        doctor.setId(++DOCTOR_COUNT);
        doctors.add(doctor);
    }

    public void update(int id, Doctor updatedDoctor){
        Doctor doctorForUpdate = show(id);
        doctorForUpdate.setName(updatedDoctor.getName());
        doctorForUpdate.setSurname(updatedDoctor.getSurname());
        doctorForUpdate.setDoctorType(updatedDoctor.getDoctorType());
        doctorForUpdate.setExperience(updatedDoctor.getExperience());
        doctorForUpdate.setPatients(updatedDoctor.getPatients());
        }


    public void delete(int id){
        doctors.removeIf(p->p.getId()==id);
    }
}
