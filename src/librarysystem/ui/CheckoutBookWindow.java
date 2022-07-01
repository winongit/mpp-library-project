package librarysystem.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.impl.BookController;
import business.impl.ControllerFactory;
import business.impl.SystemController;
import business.usecase.CheckOutBookUseCase;
import business.usecase.ControllerInterface;
import business.usecase.SearchBookUseCase;
import domain.BookCopy;
import domain.exception.BookNotFoundException;
import domain.exception.MemberNotFoundException;

public class CheckoutBookWindow extends JFrame implements LibWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
	
	private CheckoutBookWindow() {
	}

	CheckOutBookUseCase checkOutBookUseCase = ControllerFactory.createCheckOutBookUseCase();
	private boolean isInitialized = false;

	JTextField txtISBN, txtMemberID;
	JComboBox<BookCopy> cmbCopies;
	JTable jt;
	private boolean idWasValidated = false;

	public void checkOutBook() {
		// TODO Auto-generated method stub

		JPanel panelCheckoutFields = new JPanel();
		panelCheckoutFields.setLayout(null);

		this.setTitle("Checkout Book");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(20, 20, 100, 20);

		txtISBN = new JTextField(10);
		txtISBN.setBounds(110, 20, 100, 20);

		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setBounds(20, 50, 100, 20);

		txtMemberID = new JTextField(10);
		txtMemberID.setBounds(110, 50, 100, 20);

		JButton btnCheckID = new JButton("Check ID");
		btnCheckID.setBounds(110, 80, 100, 20);
		addCheckIDListener(btnCheckID);

		JLabel lblSelectCopy = new JLabel("Select Copy:");
		lblSelectCopy.setBounds(20, 110, 100, 20);

		cmbCopies = new JComboBox<BookCopy>();
		cmbCopies.setBounds(110, 110, 100, 20);

		JButton btnBackToMain = new JButton("<< Back to Main");
		addBackButtonListener(btnBackToMain);

		JButton btnCheckOut = new JButton("Confirm Checkout");
		addCheckoutBtnListener(btnCheckOut);

		JPanel pnlButtonSave = new JPanel();
		pnlButtonSave.add(btnBackToMain);
		pnlButtonSave.add(btnCheckOut);
		pnlButtonSave.setBounds(20, 150, 360, 35);
		pnlButtonSave.setBackground(Color.green);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Copy No");
		model.addColumn("Member");
		model.addColumn("Issue Date");
		model.addColumn("Due Date");

		jt = new JTable(model);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 200, 360, 150);
		panelCheckoutFields.add(sp);

		// Print CheckoutRecord

		model.addRow(new Object[] { "2", "1001", LocalDate.now(), LocalDate.now().plusDays(7) });
		model.addRow(new Object[] { "1", "1002", LocalDate.now(), LocalDate.now().plusDays(21) });

		panelCheckoutFields.add(lblMemberID);
		panelCheckoutFields.add(txtISBN);

		panelCheckoutFields.add(lblISBN);
		panelCheckoutFields.add(txtMemberID);

		panelCheckoutFields.add(btnCheckID);

		panelCheckoutFields.add(lblSelectCopy);
		panelCheckoutFields.add(cmbCopies);
		panelCheckoutFields.add(pnlButtonSave, BorderLayout.CENTER);

		this.setSize(420, 420);
		this.setVisible(true);
		this.add(panelCheckoutFields);

	}

	private void addCheckIDListener(JButton butn) {
		butn.addActionListener(evt -> {
			try {
				checkOutBookUseCase.checkOutBook(txtMemberID.getText(), txtISBN.getText());
			} catch (BookNotFoundException e) {
				JOptionPane.showMessageDialog(this,e.getMessage(), "Checkout Book", JOptionPane.ERROR_MESSAGE);
				
			} catch (MemberNotFoundException e) {
				JOptionPane.showMessageDialog(this,e.getMessage(), "Checkout Book", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

	private void addCheckoutBtnListener(JButton butn) {
		butn.addActionListener(evt -> {

			BookCopy bookCopy = (BookCopy) cmbCopies.getSelectedItem();

			if (bookCopy == null) {
				JOptionPane.showMessageDialog(this, "Pls select a book from the list.", "Save Failed",
						JOptionPane.ERROR_MESSAGE);

				return;
			} else if (!bookCopy.isAvailable()) {
				JOptionPane.showMessageDialog(this, "Selected book is not available.", "Save Failed",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!idWasValidated) {
				JOptionPane.showMessageDialog(this, "Enter valid ID then click CheckID.", "Save Failed",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			JOptionPane.showMessageDialog(this, "Save successful");

			DefaultTableModel modell = (DefaultTableModel) jt.getModel();
			BookCopy bc = (BookCopy) cmbCopies.getSelectedItem();
			modell.addRow(new Object[] { bc.getCopyNum(), txtMemberID.getText().trim(), LocalDate.now(),
					LocalDate.now().plusDays(bc.getBook().getMaxCheckoutLength()) });

			jt.setRowSelectionInterval(modell.getRowCount() - 1, modell.getRowCount() - 1);
			jt.scrollRectToVisible(new Rectangle(jt.getCellRect(modell.getRowCount() - 1, 0, true)));

			txtISBN.setText("");
			txtMemberID.setText("");
			cmbCopies.removeAllItems();
			idWasValidated = false;

		});
	}

	public boolean isInitialized() {
		return this.isInitialized;
	}

	public void isInitialized(boolean val) {
		this.isInitialized = val;
	}

	public class BookCopyRenderer extends JLabel implements ListCellRenderer<BookCopy> {
		@Override
		public Component getListCellRendererComponent(JList<? extends BookCopy> list, BookCopy bookCopy, int index,
				boolean isSelected, boolean cellHasFocus) {

			int copyNumber = bookCopy.getCopyNum();
			setText(copyNumber + ", IsAvailable:" + bookCopy.isAvailable());

			return this;
		}
	}

	@Override
	public void init() {
		checkOutBook();
	}

}
