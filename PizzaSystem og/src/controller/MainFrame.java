package controller;
import java.awt.CardLayout;
import javax.swing.JFrame;

import data.Datastorage;
import gui.Login;
import gui.CustomerScreen;
import gui.DispMenu;
import gui.EditItem;
import gui.OrderMenu;
import gui.Payment;
import gui.CardPayment;
import gui.Checkout;
import gui.StaffScreen;


public class MainFrame extends JFrame {

	private Controller cont;
	private CardLayout card;

	public MainFrame () {
		this.setTitle("Pizzaria Ordering System");
		this.setSize(1000,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.card = new CardLayout();
		this.cont = new Controller();
		this.setLayout(this.card);
		this.showLogin();
		this.setVisible(true);
	}
	
	public Controller getController() {
	 	 return cont; 
	}

	public void setController(Controller controller) { 
		 this.cont = controller; 
	}

	public void showLogin() {
	    Login p1 = new Login(this, cont);
	    this.add(p1, "Login");
	    this.card.show(this.getContentPane(), "Login");
	}

	public void showOrderMenu() {
	    OrderMenu p6 = new OrderMenu(this, cont);
	    this.add(p6, "Order Menu");
	    this.card.show(this.getContentPane(), "Order Menu");
	}
	
	public void showStaffScreen() {
		StaffScreen p2 = new StaffScreen(this, cont);
		this.add(p2, "Staff Screen");
		this.card.show(this.getContentPane(), "Staff Screen");
	 }
	
	public void showEditItem() {
		EditItem p4 = new EditItem(this, cont);
		this.add(p4, "Edit Item");
		this.card.show(this.getContentPane(), "Edit Item");
	 }

	public void showCheckout() {
		Checkout p7 = new Checkout(this, cont);
		this.add(p7, "Placed Order");
		this.card.show(this.getContentPane(), "Placed Order");
	 }

	public void showDispMenu() {
		DispMenu p3 = new DispMenu(this, cont);
		this.add(p3, "Display Menu");
		this.card.show(this.getContentPane(), "Display Menu");
	 }

	public void showCustomerScreen() { 
		CustomerScreen p5 = new CustomerScreen(this, cont);
		this.add(p5, "Customer Screen");
		this.card.show(this.getContentPane(), "Customer Screen");
	 }
	
	public void showPaymentScreen() {
		Payment p8 = new Payment(this, cont);
		this.add(p8, "Payment Screen");
		this.card.show(this.getContentPane(), "Payment Screen");
	}
	
	public void showCardPayment() {
		CardPayment p9 = new CardPayment(this, cont);
		this.add(p9, "Card Payment Screen");
		this.card.show(this.getContentPane(), "Card Payment Screen");
	}
	
	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
	 }
}