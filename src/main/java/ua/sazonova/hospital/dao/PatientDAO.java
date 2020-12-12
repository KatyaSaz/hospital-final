//package ua.sazonova.hospital.dao;
//
//import org.springframework.stereotype.Component;
//import ua.sazonova.hospital.entity.enam.Gender;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class PatientDAO {
//    private static int PATIENT_COUNT;
//    private List<Patient> patients;
//
//    {
//        patients = new ArrayList<>();
//
//        patients.add(new Patient(
//                201,
//                "Katya",
//                "Sazonova",
//                 Gender.female,
//                "07.12.1999",
//                "+380972999090",
//                Arrays.asList(
//                        new CardRecord(
//                                "Lili",
//                                "21.11.2020",
//                                "Directed to massage",
//                                RecordType.Direction),
//                        new CardRecord(
//                                "Pavel",
//                                "23.11.2020",
//                                "Make massage",
//                                RecordType.Execution),
//                        new CardRecord(
//                                "Lili",
//                                "23.11.2020",
//                                "Rheumatism",
//                                RecordType.Diagnosis)
//                )));
//
//
//        patients.add(new Patient(
//                202,
//                "Xie",
//                "Lian",
//                Gender.male,
//                "12.03.1945",
//                "+380974569090",
//                Arrays.asList(
//                        new CardRecord(
//                                "Mike",
//                                "04.12.2020",
//                                "Referral for general blood test",
//                                RecordType.Direction),
//                        new CardRecord(
//                                "Paula",
//                                "05.12.2020",
//                                "Take blood test",
//                                RecordType.Execution),
//                        new CardRecord(
//                                "Mike",
//                                "05.12.2020",
//                                "Anemia",
//                                RecordType.Diagnosis)
//                )));
//
//
//        patients.add(new Patient(
//                203,
//                "Hua",
//                "Cheng",
//                Gender.male,
//                "16.10.1950",
//                "+380638053060",
//                Arrays.asList(
//                        new CardRecord(
//                                "Lili",
//                                "05.12.2020",
//                                "Directed to the optometrist",
//                                RecordType.Direction),
//                        new CardRecord(
//                                "Peter",
//                                "06.12.2020",
//                                "Retinal surgery",
//                                RecordType.Execution),
//                        new CardRecord(
//                                "Peter",
//                                "06.12.2020",
//                                "Operation completed without problems recovery time 2 weeks",
//                                RecordType.Diagnosis)
//                )));
//
//        patients.add(new Patient(
//                204,
//                "Luo",
//                "Binghe",
//                Gender.male,
//                "06.06.1996",
//                "+380501653300",
//                new ArrayList<>()
//                ));
//        patients.add(new Patient(
//                205,
//                "Vika",
//                "Polushina",
//                Gender.female,
//                "07.11.2000",
//                "+380972280628",
//                new ArrayList<>()
//        ));
//    }
//
//    public List<Patient> index() {
//        return patients;
//    }
//
//    public Patient show(long id) {
//        return patients.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//    }
//
//    public void save(Patient person){
//        person.setId(++PATIENT_COUNT);
//        patients.add(person);
//    }
//
//    public void update(long id, Patient updatedPerson){
//
//        Patient patientToBeupdated = show(id);
//        patientToBeupdated.setName(updatedPerson.getName());
//        patientToBeupdated.setSurname(updatedPerson.getSurname());
//        patientToBeupdated.setGender(updatedPerson.getGender());
//        patientToBeupdated.setBirthDate(updatedPerson.getBirthDate());
//        patientToBeupdated.setPhone(updatedPerson.getPhone());
//
//        List<CardRecord> cardToBeUpdated =  patientToBeupdated.getRecords();
//        for(int i=0; i< cardToBeUpdated.size(); i++ ){
//            cardToBeUpdated.get(i)
//                    .setDoctorName(
//                            updatedPerson
//                                    .getRecords()
//                                    .get(i)
//                                    .getDoctorName()
//                    );
//            cardToBeUpdated.get(i)
//                    .setDate(
//                            updatedPerson
//                                    .getRecords()
//                                    .get(i)
//                                    .getDate()
//                    );
//
//            cardToBeUpdated.get(i)
//                    .setDescription(
//                            updatedPerson
//                                    .getRecords()
//                                    .get(i)
//                                    .getDescription()
//                    );
//
//            cardToBeUpdated.get(i)
//                    .setRecordType(
//                            updatedPerson
//                                    .getRecords()
//                                    .get(i)
//                                    .getRecordType()
//                    );
//        }
//    }
//
//    public void delete(long id){
//        patients.removeIf(p->p.getId()==id);
//    }
//
//}
//
