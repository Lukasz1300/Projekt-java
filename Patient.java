package git.zad1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Patient {
    private String idPatient;
    private String lastName;
    private String name;
    private String pesel;
    private LocalDate dateOfBirthPatient;

    private static List<Patient> patientList = new ArrayList<>();

    private List<Visit> visitList = new ArrayList<>();

    public Patient(String idPatient, String lastName, String name, String pesel, LocalDate dateOfBirthPatient) {
        this.idPatient = idPatient;
        this.lastName = lastName;
        this.name = name;
        this.pesel = pesel;
        this.dateOfBirthPatient = dateOfBirthPatient;
        patientList.add(this);
    }

    // Metoda pomocnicza do znalezienia pacjenta na podstawie id_pacjenta
    public static Patient findPatientByiD(String idPatient) {
        for (Patient patient : patientList) {
            if (patient.idPatient.equals(idPatient)) {
                return patient;
            }
        }
        return null;
    }

    //  zwroc pacientow ktorzy byli u minumum 5ciu roznych lekarzy
    public static List<Patient> returnPatientsWith5DifferentDoctors(List<Visit> list) {
        Map<String, Integer> patientDoctorsMap = new HashMap<>();
        // Liczymy liczbê ró¿nych lekarzy, u których ka¿dy pacjent mia³ wizytê
        for (Visit visit : list) {
            String idPatient = visit.getPatient().getIdPatient();
            patientDoctorsMap.put(idPatient, patientDoctorsMap.getOrDefault(idPatient, 0) + 1);
        }
        // Tworzymy listê pacjentów, którzy mieli wizyty u co najmniej 5 ró¿nych lekarzy
        List<Patient> patientsWithMin5DifferentDoctors = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : patientDoctorsMap.entrySet()) {
            if (entry.getValue() >= 5) {
                String idPatient = entry.getKey();
                Patient patient = findPatientByiD(idPatient);
                if (patient != null) {
                    patientsWithMin5DifferentDoctors.add(patient);
                }
            }
        }
        return patientsWithMin5DifferentDoctors;
    }

    public static List<Patient> getPatientList() {
        return patientList;
    }

    public static Patient createPatient(String[] tab) {
        String idPatient = tab[0];
        String lastName = tab[1];
        String name = tab[2];
        String pesel = tab[3];
        LocalDate dateOfBirthPatient = LocalDate.parse(tab[4], DateTimeFormatter.ofPattern("yyyy-M-d"));
        return new Patient(idPatient, lastName, name, pesel, dateOfBirthPatient);
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getDateOfBirthPatient() {
        return dateOfBirthPatient;
    }

    public void setDateOfBirthPatient(LocalDate dateOfBirthPatient) {
        this.dateOfBirthPatient = dateOfBirthPatient;
    }

    public static void setPatientList(List<Patient> patientList) {
        Patient.patientList = patientList;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "idPatient='" + idPatient + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dateOfBirthPatient=" + dateOfBirthPatient +
                ", visitList=" + visitList +
                '}';
    }
}

