import java.awt.*;
import javax.swing.*;              //  It will provide the JtextArea, JMenuBar, JMenuItems and etc
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class
editor extends JFrame implements ActionListener {

    JFrame f;

    JTextArea t;

    editor(){                             //Constructor will initialize the textarea
        f = new JFrame("Notepad");
                                        //Setting the theme of overall Application
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }
        // Create the text component
        t = new JTextArea();

        //creating the Menu
        JMenuBar menu = new JMenuBar();
                                           //Creating File option in Menu
        JMenu file = new JMenu("File");
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");
                                                      //Adding ActionListener in the File Options
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);
                                                //Making Edit option in Menu
        JMenu edit = new JMenu("Edit");
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

                                                //Adding ActionListener in the Edit Options
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        edit.add(e1);
        edit.add(e2);
        edit.add(e3);
                                                        //Creating the close Button on the MenuBar
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);
                                            //Adding the file,edit,close options to the MenuBar
        menu.add(file);
        menu.add(edit);
        menu.add(close);
                                                //Adding the MenuBar and TextArea to the Frame
        f.setJMenuBar(menu);

        f.add(t);
        f.setSize(1000 , 500);

        f.show();
    }
                                        //Function for adding functionality to each of the MenuItems
    public void actionPerformed(ActionEvent e){
            //Extracting the button pressed
        String s = e.getActionCommand();

        if(s.equals("New")){
            t.setText("");
        }

        else if(s.equals("Open")){
                                                    //Initializing the default location to open
            JFileChooser j = new JFileChooser("Desktop: ");

                                                    //invoking the openDialogbox with an integer
            int openBox = j.showOpenDialog(null);

                                            //We will know that the user want to continue
            if(openBox == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file Location
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                //String to copy the data from the chosen File

                try {
                    String s1 = "";
                    String s2 = "";
                                            //Store the whole file in fr
                    FileReader fr = new FileReader(fi);
                              //Buffer the fr variablr by character so that it can be stored in String
                    BufferedReader br = new BufferedReader(fr);

                    //Storing the first character inside s2
                    s2 = br.readLine();

                       //adding all the lines to s2 one by one
                    while ((s1 = br.readLine()) != null) {
                        s2 = s2 + '\n' + s1 ;
                    }

                    t.setText(s2);
                }
                catch(Exception et){

                }
            }
        }


        else if(s.equals("Save")){

            JFileChooser j = new JFileChooser("Desktop: ");

            int saveBox = j.showSaveDialog(null);

            if(saveBox == JFileChooser.APPROVE_OPTION){

                File fi = new File(j.getSelectedFile().getAbsoluteFile().toURI());

                try{
                    FileWriter fw = new FileWriter(fi, false);

                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(t.getText());

                    //After writing is finished we need to flush the Stream
                    bw.flush();
                    bw.close();
                }
                catch (Exception et){
                    JOptionPane.showMessageDialog(f , et.getMessage());
                }

            }

        }


        else if(s.equals("Print")) {
            try {
                t.print();
            }
            catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("Cut")){
               t.cut();
        }
        else if(s.equals("Copy")){
             t.copy();
        }
        else if(s.equals("Paste")){
             t.paste();
        }
        else if(s.equals("Close")){
            f.setVisible(false);
        }
    }
    public static void main(String[] args) {

        editor e = new editor();

    }
}