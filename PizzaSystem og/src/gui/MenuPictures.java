package gui;
import javax.swing.*;
import java.awt.*;

public class MenuPictures extends JFrame {
    
    public MenuPictures() {
        getContentPane().setBackground(new Color(255, 240, 245));
        
        this.setTitle("Picture Menu");
        this.setSize(1165, 989);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes this GUI 
        getContentPane().setLayout(null);
        
        JLabel lblPepperoni = new JLabel("New label");
        Image img = new ImageIcon(this.getClass().getResource("/pepperoni.jpeg")).getImage();
        Image modifiedimg = img.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblPepperoni.setIcon(new ImageIcon(modifiedimg));
        lblPepperoni.setBounds(20, 16, 358, 235);
        getContentPane().add(lblPepperoni);
        
        JLabel lblHawaiian = new JLabel("New label");
        Image img1 = new ImageIcon(this.getClass().getResource("/haiwaiian.jpg")).getImage();
        Image modifiedimg1 = img1.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblHawaiian.setIcon(new ImageIcon(modifiedimg1));
        lblHawaiian.setBounds(400, 16, 358, 235);
        getContentPane().add(lblHawaiian);
        
        JLabel lblBBQ = new JLabel("New label");
        Image img2 = new ImageIcon(this.getClass().getResource("/bbq.jpg")).getImage();
        Image modifiedimg2 = img2.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblBBQ.setIcon(new ImageIcon(modifiedimg2));
        lblBBQ.setBounds(20, 331, 358, 235);
        getContentPane().add(lblBBQ);

        JLabel lblCheese = new JLabel("New label");
        Image img3 = new ImageIcon(this.getClass().getResource("/cheese.jpg")).getImage();
        Image modifiedimg3 = img3.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblCheese.setIcon(new ImageIcon(modifiedimg3));
        lblCheese.setBounds(400, 331, 358, 235);
        getContentPane().add(lblCheese);
        
        JLabel lblMargherita = new JLabel("New label");
        Image img4 = new ImageIcon(this.getClass().getResource("/margherita.jpg")).getImage();
        Image modifiedimg4 = img4.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblMargherita.setIcon(new ImageIcon(modifiedimg4)); 
        lblMargherita.setBounds(783, 16, 341, 235);
        getContentPane().add(lblMargherita);
        
        JLabel lblChicago = new JLabel("New label");
        Image img5 = new ImageIcon(this.getClass().getResource("/chicago.jpg")).getImage(); 
        Image modifiedimg5 = img5.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblChicago.setIcon(new ImageIcon(modifiedimg5)); 
        lblChicago.setBounds(783, 331, 341, 235);
        getContentPane().add(lblChicago);
        
        JLabel lblLemonade = new JLabel("New label");
        Image img6 = new ImageIcon(this.getClass().getResource("/lemonade.jpg")).getImage(); 
        Image modifiedimg6 = img6.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblLemonade.setIcon(new ImageIcon(modifiedimg6)); 
        lblLemonade.setBounds(20, 648, 358, 212);
        getContentPane().add(lblLemonade);
        
        JLabel lblSprite = new JLabel("New label");
        Image img7 = new ImageIcon(this.getClass().getResource("/sprite.jfif")).getImage(); 
        Image modifiedimg7 = img7.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblSprite.setIcon(new ImageIcon(modifiedimg7)); 
        lblSprite.setBounds(400, 648, 358, 212);
        getContentPane().add(lblSprite);
        
        JLabel lblCoke = new JLabel("New label");
        Image img8 = new ImageIcon(this.getClass().getResource("/coke.jpg")).getImage(); 
        Image modifiedimg8 = img8.getScaledInstance(358, 253, java.awt.Image.SCALE_SMOOTH);
        lblCoke.setIcon(new ImageIcon(modifiedimg8)); 
        lblCoke.setBounds(779, 648, 345, 212);
        getContentPane().add(lblCoke);
        
        JLabel lblPep = new JLabel("Pepperoni Pizza");
        lblPep.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPep.setBounds(111, 267, 171, 29);
        getContentPane().add(lblPep);
        
        JLabel lblHawai = new JLabel("Hawaiian Pizza");
        lblHawai.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblHawai.setBounds(503, 267, 180, 20);
        getContentPane().add(lblHawai);
        
        JLabel lblBBQChick = new JLabel("BBQ Chicken Pizza");
        lblBBQChick.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblBBQChick.setBounds(101, 582, 195, 20);
        getContentPane().add(lblBBQChick);
        
        JLabel lblCheesePiz = new JLabel("Classic Cheese Pizza");
        lblCheesePiz.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblCheesePiz.setBounds(476, 582, 228, 20);
        getContentPane().add(lblCheesePiz);
        
        JLabel lblMPizza = new JLabel("Margherita Pizza");
        lblMPizza.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblMPizza.setBounds(867, 267, 195, 29);
        getContentPane().add(lblMPizza);
        
        JLabel lblCPizza = new JLabel("Chicago Deep Dish Pizza");
        lblCPizza.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblCPizza.setBounds(823, 582, 270, 29);
        getContentPane().add(lblCPizza);
        
        JLabel lblLDrink = new JLabel("Fresh Lemonade");
        lblLDrink.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblLDrink.setBounds(112, 876, 195, 20);
        getContentPane().add(lblLDrink);
        
        JLabel lblSDrink = new JLabel("Sprite");
        lblSDrink.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblSDrink.setBounds(541, 876, 96, 20);
        getContentPane().add(lblSDrink);
        
        JLabel lblCDrink = new JLabel("Coke");
        lblCDrink.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblCDrink.setBounds(929, 876, 86, 20);
        getContentPane().add(lblCDrink);
    }
}