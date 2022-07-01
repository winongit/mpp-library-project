package librarysystem.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.impl.ControllerFactory;
import business.usecase.AddBookUseCase;
import business.usecase.GetAuthorUseCase;
import business.usecase.GetBookUseCase;
import domain.Author;
import domain.Book;
import domain.BookCopy;
import librarysystem.util.Util;

public class AddBookWindow extends JFrame implements LibWindow {
	public static final AddBookWindow INSTANCE = new AddBookWindow();
	private static final long serialVersionUID = 1L;
	private boolean isInitialized = false;

	private AddBookUseCase addBookUseCase = ControllerFactory.createAddBookUseCase();
	private GetBookUseCase getBookUseCase = ControllerFactory.createGetBookUseCase();

	private JPanel mainPanel = new JPanel();
	private JPanel topPanel;
	private JPanel outerMiddle;

	private JTextField txtISBN, txtTitle, txtNoOfCopy;

	private DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
	private List<JCheckBox> jCheckBoxs = new ArrayList<>();
	private JComboBox<Integer> cmbMaxCheckOutLength;
	private JScrollPane jScrollPane;
	JTable jt;

	DefaultTableModel jtmodel = new DefaultTableModel();

	
	

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

	private JScrollPane initializeTable() {
		// jTable
		jtmodel.addColumn("ISBN");
		jtmodel.addColumn("Copy Number");
		jtmodel.addColumn("Book Title");
		jtmodel.addColumn("Availability");

		jt = new JTable(jtmodel);

		jt.getColumnModel().getColumn(0).setPreferredWidth(20);
		jt.getColumnModel().getColumn(1).setPreferredWidth(27);
		jt.getColumnModel().getColumn(3).setPreferredWidth(70);
		jt.getColumnModel().getColumn(3).setPreferredWidth(22);
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(310, 20, 375, 340);

		// load books
		List<Book> dataa = getBookUseCase.getBookCollection();

		for (Book lm : dataa) {
			String isbn = lm.getIsbn();
			String title = lm.getTitle();

			for (BookCopy bc : lm.getCopies()) {
				jtmodel.addRow(new Object[] { isbn, bc.getCopyNum(), title, bc.isAvailable() });
			}

		}
		return sp;

	}

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

		jCheckBoxs.forEach(s -> s.setSelected(false));
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
		JPanel p = new JPanel();

		authors.forEach(author -> {
			JCheckBox box = new JCheckBox(author.getFullName());
			jCheckBoxs.add(box);
			p.add(box);
		});
		rightPanel.add(p);

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
		jScrollPane = initializeTable();

		

		this.outerMiddle.add(jScrollPane, "South");
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

	private void attachAddBookButtonListener(JButton butn) {
		butn.addActionListener((evt) -> {

			try {
				String isbn = txtISBN.getText().trim();
				String title = txtTitle.getText().trim();
				int maxCheckoutPeriod = (int) cmbMaxCheckOutLength.getSelectedItem();
				List<Author> selectedAuthors = new ArrayList<Author>();
				int noOfCopy = Integer.valueOf(txtNoOfCopy.getText());
				jCheckBoxs.forEach(box -> {
					List<Author> authors = getAllAuthors();

					authors.forEach(auth -> {
						if (box.getText().equals(auth.getFullName())) {
							selectedAuthors.add(auth);
						}
					});

				});

				Book book = new Book(isbn, title, maxCheckoutPeriod, selectedAuthors);

				BookCopy bookcopy = new BookCopy(book, noOfCopy, noOfCopy > 0 ? true : false);
				addBookUseCase.addBook(book);
				book.updateCopies(bookcopy);

				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				model.setRowCount(0); // clear data

				// load books
				GetBookUseCase getBookUseCase = ControllerFactory.createGetBookUseCase();
				List<Book> data = getBookUseCase.getBookCollection();

				for (Book bk : data) {
					String strISBN = bk.getIsbn();
					String strTitle = bk.getTitle();

					for (BookCopy bc : bk.getCopies()) {
						model.addRow(new Object[] { strISBN, bc.getCopyNum(), strTitle, bc.isAvailable() });
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Save Failed!", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this,
					"The book " + txtTitle.getText().trim() + " has been added " + "to the collection!");
			clearData();

			// load books
			List<Book> dataa = getBookUseCase.getBookCollection();
			jtmodel = new  DefaultTableModel();
			for (Book lm : dataa) {
				String isbn = lm.getIsbn();
				String title = lm.getTitle();
				for (BookCopy bc : lm.getCopies()) {
					jtmodel.addRow(new Object[] { isbn, bc.getCopyNum(), title, bc.isAvailable() });
				}

			}
			// authors.clear();
			// listModel.clear();
			// modelBookCopies.clear();

		});
	}

	public void updateData() {
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
