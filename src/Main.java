import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) {

        //Variable innit
        JTextField aTF,bTF,cTF,errorField,credits,size,speed;
        DefaultListModel<String> types;
        JList<String> typeList;
        JButton displayButton,discord;
        JFrame mainFrame;
        JSlider sizeSlider,speedSlider;
        ImageIcon imageIcon = new ImageIcon("src/images/appIcon.png");
        Image icon = imageIcon.getImage();


        //The list of all the calculation types
        types = new DefaultListModel<>();

        types.addElement("Turning Point: y = a(x - b)^2 + c");
        types.addElement("x-intercept: y = (x + a)(x + b) + c");
        types.addElement("Circle: (x + a)^2 + (x + b)^2 = c^2");
        types.addElement("exponential: y = a^(x + b) + c");
        types.addElement("power: y = ax^b + c");

        typeList = new JList<>(types);
        typeList.setBounds(10,50,200,150);


        //Text field for the variable of the first number
        aTF = new JTextField();
        aTF.setToolTipText("'a' value goes here");
        aTF.setBounds(237,65,75,30);
        aTF.setFont(new Font("font", Font.BOLD,14));


        //Text field for the variable of the second number
        bTF = new JTextField();
        bTF.setToolTipText("'b' value goes here");
        bTF.setBounds(237,100,75,30);
        bTF.setFont(new Font("font", Font.BOLD,14));


        //Text field for the variable of the third number
        cTF = new JTextField();
        cTF.setToolTipText("'c' value goes here");
        cTF.setBounds(237,135,75,30);
        cTF.setFont(new Font("font", Font.BOLD,14));


        //Field to display mistakes made (if the user placed a non-numerical, this is where the shouting would happen)
        errorField = new JTextField();
        errorField.setEditable(false);
        errorField.setBounds(10,245,325,80);
        errorField.setBackground(Color.GRAY);
        errorField.setFont(new Font("font", Font.PLAIN,15));


        //Bragger 9000 (lol)
        credits = new JTextField();
        credits.setEditable(false);
        credits.setText("Made by epicnuss55     Discord: epic#6926");
        credits.setBounds(10,220,240,20);


        //Displays the current size of the grid (will change dynamically on the respective slider moving)
        size = new JTextField();
        size.setBounds(275,5,61,20);
        size.setEditable(false);
        size.setText("Size: 60");


        //Slider to change the size of the grid
        sizeSlider = new JSlider(20,100,60);
        sizeSlider.setBounds(5,5,270,20);

        sizeSlider.addChangeListener(e -> size.setText("Size: " + sizeSlider.getValue()));


        //Displays the current movement speed of the camera (will change dynamically on the respective slider moving)
        speed = new JTextField();
        speed.setBounds(275,30,61,20);
        speed.setEditable(false);
        speed.setText("Speed: 1");


        //Slider to change the speed of the cameras movement around the grid
        speedSlider = new JSlider(1,10,1);
        speedSlider.setBounds(5,30,270,20);

        speedSlider.addChangeListener(e -> speed.setText("Speed: " + speedSlider.getValue()));


        //Button that'll slap all the variables put in by the user together and display the graph
        displayButton = new JButton("display");
        displayButton.setBounds(225,175,100,35);

        displayButton.addActionListener(e -> {

            //checks if the inputted text is a valid number
            if (checker.isNumeric(aTF.getText()) && checker.isNumeric(bTF.getText()) && checker.isNumeric(cTF.getText()) && typeList.getSelectedIndex() != -1){

                errorField.setText("Displaying");

                //create an application JPanel and JFrame object to show the graph
                application app = new application(typeList.getSelectedIndex()+1,Integer.parseInt(aTF.getText()),Integer.parseInt(bTF.getText()),Integer.parseInt(cTF.getText()),sizeSlider.getValue(),speedSlider.getValue());
                JFrame frame = new JFrame("Display");

                frame.add(app);

                //Adds keyListeners to allow for the camera to be moved around the graph
                frame.addKeyListener(new KeyListener() {
                    @Override public void keyTyped(KeyEvent e) {}

                    //keyPressed event
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) app.W = true;
                        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) app.A = true;
                        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) app.S = true;
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) app.D = true;
                    }

                    //keyReleased event
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) app.W = false;
                        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) app.A = false;
                        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) app.S = false;
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) app.D = false;
                    }
                });

                //sets the frames' size and adds the icon
                frame.setSize(500,500);
                frame.setVisible(true);
                frame.setIconImage(icon);
            }

            //Stuff to make sure no glitches happens
            else if (checker.isNumeric(aTF.getText()) && checker.isNumeric(bTF.getText()) && checker.isNumeric(cTF.getText())){
                errorField.setText("Please select a type");
            }
            else
                errorField.setText("fill ALL up boxes with whole numbers");
        });


        //Discord button, takes you to my discord server
        discord = new JButton();
        discord.setBounds(255,220,80,20);
        discord.setText("Discord");

        discord.addActionListener(e -> {
            try {
                URI discordurl = new URI("https://discord.gg/SNRYSGa");
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(discordurl.toURL().toURI());
            } catch (URISyntaxException | IOException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        });


        //Creates the main frame
        mainFrame = new JFrame("Graph Function Visualiser");

        //Adds literally everything
        mainFrame.add(speed);
        mainFrame.add(speedSlider);
        mainFrame.add(sizeSlider);
        mainFrame.add(size);
        mainFrame.add(displayButton);
        mainFrame.add(cTF);
        mainFrame.add(aTF);
        mainFrame.add(bTF);
        mainFrame.add(credits);
        mainFrame.add(typeList);
        mainFrame.add(displayButton);
        mainFrame.add(errorField);
        mainFrame.add(discord);
        mainFrame.setIconImage(icon);

        //main frame configs
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(360,375);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
}