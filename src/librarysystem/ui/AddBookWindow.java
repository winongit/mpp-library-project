package librarysystem.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.impl.ControllerFactory;
import business.usecase.GetAuthorUseCase;
import domain.Author;
import librarysystem.util.JCheckBoxList;
import librarysystem.util.Util;

public class AddBookWindow extends JFrame implements LibWindow {
	public static final AddBookWindow INSTANCE = new AddBookWindow();
	private static final long serialVersionUID = 1L;
	private boolean isInitialized = false;

	private JPanel mainPanel = new JPanel();
	private JPanel topPanel;
	private JPanel outerMiddle;

	private JTextField txtISBN, txtTitle, txtNoOfCopy;

	private DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
	private JCheckBoxList lstAuthors = new JCheckBoxList(model);

	private JComboBox<Integer> cmbMaxCheckOutLength;

	// Constructor
	private AddBookWindow() {
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public void clearData() {
		this.txtISBN.setText("");
		this.txtTitle.setText("");
		this.txtNoOfCopy.setText("");
	}

	public List<Author> getAllAuthors() {
		GetAuthorUseCase ac = ControllerFactory.createGetAuthorController();
		List<Author> authors = ac.getAllAuthors();
		return authors;
	}

	public void defineTopPanel() {
		this.topPanel = new JPanel();
		JLabel AddBookLabel = new JLabel("Add New Book");
		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		this.topPanel.setLayout(new FlowLayout(0));
		this.topPanel.add(AddBookLabel);
	}

	public void defineOuterMiddle() {
		this.outerMiddle = new JPanel();
		this.outerMiddle.setLayout(new BorderLayout());
		JPanel middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(1, 25, 25);
		middlePanel.setLayout(fl);
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, 1));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));

		// Labels
		JLabel lblISBN = new JLabel("ISBN");
		JLabel lblTitle = new JLabel("Title");
		JLabel lblAuthors = new JLabel("Authors");
		JLabel lblMaxCheckOutLength = new JLabel("Maximum Checkout Length");
		JLabel lblNumberOfCopies = new JLabel("Book Copies");
		
		this.txtISBN = new JTextField(20);
		this.txtTitle = new JTextField(10);
		this.txtNoOfCopy = new JTextField(10);
		
		this.cmbMaxCheckOutLength = new JComboBox<Integer>();
		cmbMaxCheckOutLength.addItem(21);
		cmbMaxCheckOutLength.addItem(7);

		leftPanel.add(lblISBN);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		leftPanel.add(lblTitle);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		leftPanel.add(lblAuthors);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		leftPanel.add(lblMaxCheckOutLength);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
		leftPanel.add(lblNumberOfCopies);

		// TextField, JList, JCombo
		rightPanel.add(this.txtISBN);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		rightPanel.add(this.txtTitle);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		// load authors
		List<Author> authors = getAllAuthors();
		authors.forEach(author -> model.addElement(new JCheckBox(author.getFullName())));
		rightPanel.add(this.lstAuthors);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		rightPanel.add(this.cmbMaxCheckOutLength);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		rightPanel.add(this.txtNoOfCopy);
		
		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		this.outerMiddle.add(middlePanel, "North");
		
		// Buttons
		JButton btnBackToMain = new JButton("<< Back to Main");
		addBackButtonListener(btnBackToMain);
		
		JButton btnAddBook = new JButton("Add Book");
		attachAddBookButtonListener(btnAddBook);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(1));
		buttonPanel.add(btnBackToMain);
		buttonPanel.add(btnAddBook);
		this.outerMiddle.add(buttonPanel, "Center");
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

	private void attachAddBookButtonListener(JButton butn) {
	butn.addActionListener((evt) -> {
	
		//this.displayInfo("The book " + txtTitle + " has been added " + "to the collection!");
		LibrarySystem.INSTANCE.setVisible(true);
		clearData();
	});
	}

	public void updateData() {
	}

	@Override
	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		mainPanel.add(this.topPanel, BorderLayout.NORTH);
		mainPanel.add(this.outerMiddle, BorderLayout.CENTER);

		getContentPane().add(mainPanel);
		isInitialized(true);
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

}
