import javax.swing.JTextArea;

public class RoomAvailabilityObserver implements Observer {
    private JTextArea textArea;

    public RoomAvailabilityObserver(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void update(String message) {
        textArea.append(message + "\n");
    }
}
