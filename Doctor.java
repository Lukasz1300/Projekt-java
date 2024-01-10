package git.zad1;

import java.time.LocalDate;
import java.util.*;

public class Doctor {
    private String idDoctor;
    private String lastName;
    private String name;
    private String specialty;
    private LocalDate dateOfBirthDoctor;
    private String nationalInsuranceNumber; // nip
    private String pesel;

    private static List<Doctor> doctorList = new ArrayList<>();
    private List<Visit> visitList = new ArrayList<>();

    public Doctor(String idDoctor, String lastName, String name, String specialty, LocalDate dateOfBirthDoctor, String nationalInsuranceNumber, String pesel) {
        this.idDoctor = idDoctor;
        this.lastName = lastName;
        this.name = name;
        this.specialty = specialty;
        this.dateOfBirthDoctor = dateOfBirthDoctor;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        this.pesel = pesel;
        doctorList.add(this);
    }

    public static Doctor findDoctorPoId(String idDoctor) {
        for (Doctor doctor : doctorList) {
            if (doctor.getIdDoctor().equals(idDoctor)) {
                return doctor;
            }
        }
        return null;
    }

    //      zwroc lekarzy exclusive - czyli takich ktorzy przyjmowali tylko jednego pacjenta
    public static List<Doctor> returnDoctorsWithOnePatient(List<Visit> list) {
        Map<String, Integer> doctorPatient = new HashMap<>();
        // Liczymy liczbê pacjentów, dla ka¿dego lekarza
        for (Visit visit : list) {
            String idDoctor = visit.getDoctor().getIdDoctor();
            doctorPatient.put(idDoctor, doctorPatient.getOrDefault(idDoctor, 0) + 1);
        }
        // Tworzymy listê lekarzy, którzy mieli tylko jednego pacjenta
        List<Doctor> doctorsWithOnePatient = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : doctorPatient.entrySet()) {
            if (entry.getValue() == 1) {
                String idDoctor = entry.getKey();
                Doctor doctor = findDoctorPoId(idDoctor);
                if (doctor != null) {
                    doctorsWithOnePatient.add(doctor);
                }
            }
        }
        return doctorsWithOnePatient;
    }

    //  wypisz top 5 najstarszych lekarzy
    public static List<Doctor> findTop5OldestDoctors(List<Doctor> doctorList) {
        // Sortujemy listê lekarzy na podstawie daty urodzenia w porz¹dku malej¹cym
        Collections.sort(doctorList, Comparator.comparing(Doctor::getDateOfBirthDoctor).reversed());
        // Zwracamy top 5 najstarszych lekarzy
        int limit = Math.min(doctorList.size(), 5);
        return doctorList.subList(0, limit);
    }

    // która specalizacja cieszy siê najwiêkszym powodzeniem
    public static String theMostPopularSpecialization(List<Doctor> list) {
        Map<String, Integer> specializationsCounter = new HashMap<>();
        for (Doctor doctor : list) {
            String specializations = doctor.getSpecialty();
            specializationsCounter.put(specializations, specializationsCounter.getOrDefault(specializations, 0) + 1);
        }
        List<Map.Entry<String, Integer>> specializationsList = new ArrayList<>(specializationsCounter.entrySet());
        specializationsList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        return specializationsList.get(0).getKey();
    }

    public static Doctor createADoctor(String[] tab) {
        String idDoctor = tab[0];
        String lastName = tab[1];
        String name = tab[2];
        String specialty = tab[3];
        LocalDate dateOfBirthDoctor = LocalDate.parse(tab[4]);
        String nationalInsuranceNumber = tab[5];
        String pesel = tab[6];
        return new Doctor(idDoctor, lastName, name, specialty, dateOfBirthDoctor, nationalInsuranceNumber, pesel);
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public LocalDate getDateOfBirthDoctor() {
        return dateOfBirthDoctor;
    }

    public void setDateOfBirthDoctor(LocalDate dateOfBirth) {
        this.dateOfBirthDoctor = dateOfBirth;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public static List<Doctor> getDoctorList() {
        return doctorList;
    }

    public static void setDoctorList(List<Doctor> doctorList) {
        Doctor.doctorList = doctorList;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "idDoctor='" + idDoctor + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", dateOfBirth=" + dateOfBirthDoctor +
                ", nationalInsuranceNumber='" + nationalInsuranceNumber + '\'' +
                ", pesel='" + pesel + '\'' +
                ", visitList=" + visitList +
                '}';
    }
}




