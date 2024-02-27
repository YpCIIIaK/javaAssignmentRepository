import javax.swing.*;

class ResultTextAreaObserver implements Observer {
    private JTextArea resultTextArea;

    public ResultTextAreaObserver(JTextArea resultTextArea) {
        this.resultTextArea = resultTextArea;
    }

    @Override
    public void update(String message) {
        if (message.equals("Reservation Submitted")) {
            resultTextArea.setText("Reservation submitted successfully.");
        }
    }
}