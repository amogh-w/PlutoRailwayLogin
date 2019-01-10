import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

public class TicketBooking extends JFrame {
    private JButton book_button;
    private JPanel booking_panel;
    private JTextField age_field;
    private JTextField name_field;
    private JComboBox<String> train_box;
    private JLabel name_label;
    private JLabel age_label;
    private JLabel train_name_label;

    TicketBooking() {
        booking_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(booking_panel);
        setTitle("Enter Passenger Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTrainList();

        book_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document mango_document = new Document();
                try {
                    PdfWriter mango_writer = PdfWriter.getInstance(mango_document, new FileOutputStream("ticket.pdf"));
                    mango_document.open();
                    mango_document.add(new Paragraph("Name: " + name_field.getText()));
                    mango_document.add(new Paragraph("Age: " + age_field.getText()));
                    mango_document.add(new Paragraph("Train: " + train_box.getSelectedItem()));
                    mango_document.close();
                    mango_writer.close();
                    JOptionPane.showMessageDialog(booking_panel, "Ticket was successfully booked!");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void createTrainList() {
        train_box.addItem("Chennai Express");
        train_box.addItem("Rajdhani Express");
        train_box.addItem("Japan Superfast");
        train_box.setSelectedItem(null);
    }
}