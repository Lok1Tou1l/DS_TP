import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.border.EmptyBorder;
public class Client{


  public static void main(String [] args){
    final File [] fileToSend = new File[1];
    JFrame frame = new JFrame("Client");
    frame.setSize(600,600);
    frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel title = new JLabel("File Sender");
    title.setFont(new Font("Arial", Font.BOLD, 25));
    title.setBorder(new EmptyBorder(20,0,10,0));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel fileName = new JLabel("Choose a file to send.");
    fileName.setFont(new Font( "Arial", Font.BOLD, 20));
    fileName.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel btnsPanel = new JPanel();
    btnsPanel.setBorder(new EmptyBorder(75,0,10,0));

    JButton sendFileBtn = new JButton("Send File");
    sendFileBtn.setPreferredSize(new Dimension(150,75));
    sendFileBtn.setFont(new Font("Arial", Font.BOLD , 20));
    sendFileBtn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(fileToSend[0] == null){
          fileName.setText("Please choose a file first.");
        }
        else{
          try{
            FileInputStream fis = new FileInputStream(fileToSend[0].getAbsolutePath());
            Socket socket = new Socket("192.168.8.104",6666);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String filename = fileToSend[0].getName();
            byte[] fileNameBytes = filename.getBytes();
        
            byte[] fileContentBytes = new byte[(int)fileToSend[0].length()];
            fis.read(fileContentBytes);

            dos.writeInt(fileNameBytes.length);
            dos.write(fileNameBytes);

            dos.writeInt(fileContentBytes.length);
            dos.write(fileContentBytes);

          }catch(Exception e2){
            System.out.println(e2);
          }

        }
      }
    });
    
    JButton chooseFileBtn = new JButton("Choose File");
    chooseFileBtn.setPreferredSize(new Dimension(150,75));
    chooseFileBtn.setFont(new Font("Arial", Font.BOLD , 20));
    chooseFileBtn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to send");

        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
          fileToSend[0] = fileChooser.getSelectedFile();
          fileName.setText("The file you want to send is: "+fileToSend[0].getName());
          
        }
      }
    });

    

    btnsPanel.add(sendFileBtn);
    btnsPanel.add(chooseFileBtn);
 

    frame.add(title);
    frame.add(fileName);
    frame.add(btnsPanel);
    frame.setVisible(true);   
  }
}
