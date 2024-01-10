package git.zad1;

import obiektowosc.asocjacje.pd.Projekt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Visit {
    private LocalDate dateMedicalVisit;
    private Doctor doctor;
    private Patient patient;

    private static List<Visit> visitList = new ArrayList<>();

    public Visit(LocalDate dateMedicalVisit, Doctor doctor, Patient patient) {
        this.dateMedicalVisit = dateMedicalVisit;
        this.doctor = doctor;
        this.patient = patient;

        if (this.doctor != null) {
            this.doctor.getVisitList().add(this);  // Dodajemy wizytê do listy wizyt lekarza
        }
        if (this.patient != null) {
            this.patient.getVisitList().add(this); // Dodajemy wizytê do listy wizyt pacjenta
        }
        visitList.add(this);  // Dodajemy wizytê do ogólnej listy wizyt
    }

    // którego roku by³o najwiêcej wizyt
    public static int yearWithTheMostVisits(List<Visit> list) {
        Map<Integer, Integer> yearCounter = new HashMap<>();
        for (Visit visit : list) {
            int year = visit.getDateMedicalVisit().getYear();
            yearCounter.put(year, yearCounter.getOrDefault(year, 0) + 1);
        }
        int mostVisit = 0;
        int yearWithmostVisit = 0;
        for (Map.Entry<Integer, Integer> entry : yearCounter.entrySet()) {
            int year = entry.getKey();
            int numberOfVisits = entry.getValue();
            if (numberOfVisits > mostVisit) {
                mostVisit = numberOfVisits;
                yearWithmostVisit = year;
            }
        }
        return yearWithmostVisit;
    }

    // Metoda do znalezienia pacjenta, który mia³ najwiêcej wizyt
    public static Patient findPatientWithMostVisits(List<Patient> list) {
        if (Patient.getPatientList() == null) {
            throw new RuntimeException("List is empty");
        }
        Patient patientMaxVisit = list.get(0);
        for (Patient x : list) {
            if (x.getVisitList().size() > patientMaxVisit.getVisitList().size()) {
                patientMaxVisit = x;
            }
        }
        return patientMaxVisit;
    }


    // Metoda do znalezienia lekarza, który mia³ najwiêcej wizyt
    public static Doctor findADoctorMostVisits(List<Doctor> list) {
        Doctor doctorWithTheMostVisits = Collections.max(list, Comparator.comparingInt(Visit::downloadDoctorVisitNumber));
        return doctorWithTheMostVisits;
    }

    private static int downloadDoctorVisitNumber(Doctor doctor) {
        int numberOfVisits = 0;
        for (Visit visit : visitList) {
            if (visit.getDoctor().getIdDoctor().equals(doctor.getIdDoctor())) {
                numberOfVisits++;
            }
        }
        return numberOfVisits;
    }

    public static Visit createVisit(String[] tab) {
        String idDoctor = tab[0];
        String idPatient = tab[1];
        LocalDate dateMedicalVisit = LocalDate.parse(tab[2], DateTimeFormatter.ofPattern("yyyy-M-d"));

        Doctor doctor = Doctor.findDoctorPoId(idDoctor); // ZnajdŸ obiekt lekarza na podstawie idLekarza
        Patient patient = Patient.findPatientByiD(idPatient); // ZnajdŸ obiekt pacjenta na podstawie idPacjenta
        return new Visit(dateMedicalVisit, doctor, patient); // Tworzenie instancji Wizyta
    }

    public LocalDate getDateMedicalVisit() {
        return dateMedicalVisit;
    }

    public void setDateMedicalVisit(LocalDate dateMedicalVisit) {
        this.dateMedicalVisit = dateMedicalVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public static List<Visit> getVisitList() {
        return visitList;
    }

    public static void setVisitList(List<Visit> visitList) {
        Visit.visitList = visitList;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "dateMedicalVisit=" + dateMedicalVisit +
                '}';
    }
}



