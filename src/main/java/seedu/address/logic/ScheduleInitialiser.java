package seedu.address.logic;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.storage.ScheduleManager;

/**
 * Initializes the schedule JSON file with default schedules for all doctors in the address book.
 */
public class ScheduleInitialiser {

    /**
     * Synchronises the schedule file so it always covers the current 7-day window.
     */
    public static void initialize(Model model) {
        List<Doctor> doctors = model.getAddressBook().getPersonList().stream()
                .filter(p -> p instanceof Doctor)
                .map(p -> (Doctor) p)
                .toList();

        ScheduleManager.syncSchedules(doctors);

        List<Patient> patients = model.getPatientData().getPersonList().stream()
                .filter(p -> p instanceof Patient)
                .map(p -> (Patient) p)
                .toList();
        patients.forEach(patient -> {
            try {
                ScheduleManager.syncPatientSchedule(patient);
            } catch (Exception e) {
                // Ignore startup repair failures so the app still launches.
            }
        });
    }
}
