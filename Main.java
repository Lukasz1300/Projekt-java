package git.zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String line;
        boolean firstLineDoctor = true;
        try (BufferedReader brDoctor = new BufferedReader(new FileReader("src/git/zad1/lekarze.txt"))) {
            while ((line = brDoctor.readLine()) != null) {
                if (firstLineDoctor) {
                    firstLineDoctor = false;
                    continue; // Pomijamy dalsze przetwarzanie tej linii
                }
                String[] personalDataDoctor = line.split("\t");
                Doctor.createADoctor(personalDataDoctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean firstLinePatient = true;
        try (BufferedReader brPatient = new BufferedReader(new FileReader("src/git/zad1/pacjenci.txt"))) {
            while ((line = brPatient.readLine()) != null) {
                if (firstLinePatient) {
                    firstLinePatient = false;
                    continue;
                }
                String[] personalDataPatient = line.split("\t");
                Patient.createPatient(personalDataPatient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean firstLineVisit = true;
        try (BufferedReader brWizyta = new BufferedReader(new FileReader("src/git/zad1/wizyty.txt"))) {
            while ((line = brWizyta.readLine()) != null) {
                if (firstLineVisit) {
                    firstLineVisit = false;
                    continue;
                }
                String[] detailsFromTheVisit = line.split("\t");
                Visit.createVisit(detailsFromTheVisit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(Doctor.getDoctorList());
//        System.out.println(Patient.getPatientList());
//        System.out.println(Visit.getVisitList());

        System.out.println("Metoda do znalezienia pacjenta, który mia³ najwiêcej wizyt");
        System.out.println(Visit.findPatientWithMostVisits(Patient.getPatientList()));
        System.out.println("Metoda do znalezienia lekarza, który mia³ najwiêcej wizyt");
        System.out.println(Visit.findADoctorMostVisits((Doctor.getDoctorList())));
        System.out.println("która specalizacja cieszy siê najwiêkszym powodzeniem");
        System.out.println(Doctor.theMostPopularSpecialization(Doctor.getDoctorList()));
        System.out.println("którego roku by³o najwiêcej wizyt");
        System.out.println(Visit.yearWithTheMostVisits(Visit.getVisitList()));
        System.out.println("wypisz top 5 najstarszych lekarzy");
        System.out.println(Doctor.findTop5OldestDoctors(Doctor.getDoctorList()));
        System.out.println("zwroc pacientow ktorzy byli u minumum 5ciu roznych lekarzy");
        System.out.println(Patient.returnPatientsWith5DifferentDoctors(Visit.getVisitList()));
        System.out.println("zwroc lekarzy exclusive - czyli takich ktorzy przyjmowali tylko jednego pacjenta");
        System.out.println(Doctor.returnDoctorsWithOnePatient(Visit.getVisitList()));
    }
}