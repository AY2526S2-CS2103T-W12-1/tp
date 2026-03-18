package seedu.address.storage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import seedu.address.model.appointment.Appointment;

/**
 * Manages access to the schedule data stored in the schedule JSON file.
 */
public class ScheduleManager {

    private static final String FILE_PATH = "data/schedule.json";

    public static Map<String, String> getScheduleIgnoreCase(String doctor, String date) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Map<String, Map<String, String>>> data =
                    mapper.readValue(new File(FILE_PATH), Map.class);

            for (String doctorName : data.keySet()) {

                if (doctorName.equalsIgnoreCase(doctor)) {

                    Map<String, Map<String, String>> doctorSchedule = data.get(doctorName);

                    return doctorSchedule.get(date);
                }
            }

            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addAppt(Appointment appt) {
        String doctorName = appt.getDocName();
        String patName = appt.getPatName();
        String date = appt.getDate();
        String time = appt.getTime();
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(FILE_PATH);
            Map<String, Object> data = mapper.readValue(file, Map.class);

            Map<String, String> slots = getScheduleIgnoreCase(doctorName, date);
            if (slots == null) {
                throw new IOException("Doctor or date not found!");
            }
            slots.put(time, patName);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
