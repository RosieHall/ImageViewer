import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Recommended to make your program class a JFrame
public class ImageStartup extends JFrame {

    public ImageStartup() {
        super("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400,400));

        // Instantiate our image panel class and add this panel to the
        // content pane of the JFrame

        try {
            ImageIcon imgIcon = new ImageIcon(ImageIO.read(new File("src/main/resources/eyes_icon.png")));
            setIconImage(imgIcon.getImage());
        } catch (IOException e) {
            System.out.println("Failed to load icon: " + e.getMessage());
        }

        ImagePanel imagePanel = new ImagePanel();
        Container content = this.getContentPane();
        content.add(imagePanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        this.setJMenuBar(menuBar);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File
                (System.getProperty("user.home") +
                        System.getProperty("file.separator")+ "Pictures"));

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(ImageStartup.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    imagePanel.getImage(file);
                    pack();

                }
            }
        });

        // Read the image into the panel. The image file must be at
        // the top level of your project directory


        this.setSize(400, 400);



        // By making our JFrame visible, the JVM will call the
        // paintComponent() method of any Jpanels registered to the
        // JFrame
        this.setVisible(true);



    }

    public static void main(String[] args) {new ImageStartup();}
}

