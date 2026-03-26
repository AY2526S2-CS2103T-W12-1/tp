package seedu.address.ui;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * A panel containing the schedule of a doctor.
 */
public class SchedulePanel extends UiPart<Region> {

    private static final String FXML = "SchedulePanel.fxml";

    @FXML
    private GridPane scheduleGrid;

    public SchedulePanel() {
        super(FXML);
    }

    /**
     * Initializes the schedule panel by setting up the grid layout and styling.
     */
    @FXML
    public void initialize() {
        // allow grid to resize
        scheduleGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // make columns behave nicely
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(80);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);

        scheduleGrid.getColumnConstraints().addAll(col1, col2);
    }

    /**
     * Displays the given schedule on the panel.
     *
     * @param schedule A map of time slots to patient names (or null if available).
     */
    public void displaySchedule(Map<String, String> schedule) {
        scheduleGrid.getChildren().clear();

        int row = 0;

        for (Map.Entry<String, String> entry : schedule.entrySet()) {
            String time = entry.getKey();
            String value = entry.getValue();

            // Time label
            Label timeLabel = new Label(time);
            timeLabel.getStyleClass().add("time-label");
            scheduleGrid.add(timeLabel, 0, row);

            // Slot block
            Region slot = new Region();
            slot.setPrefSize(80, 30);

            if (value == null) {
                slot.getStyleClass().add("slot-available");
            } else {
                slot.getStyleClass().add("slot-booked");
            }

            scheduleGrid.add(slot, 1, row);

            row++;
        }
    }
}
