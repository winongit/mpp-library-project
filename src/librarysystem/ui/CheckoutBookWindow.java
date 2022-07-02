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
import business.usecase.BookCopyUseCase;
import business.usecase.CheckOutBookUseCase;
import business.usecase.ControllerInterface;
import business.usecase.SearchBookUseCase;
import domain.BookCopy;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;
import domain.exception.BooCopyNotAvailableException;
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

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(110, 80, 100, 20);
		addCheckIDListener(btnCheckout);

		JButton btnBackToMain = new JButton("Back to Main");
		addBackButtonListener(btnBackToMain);

		JPanel pnlButtonSave = new JPanel();
		pnlButtonSave.add(btnBackToMain);
		pnlButtonSave.setBounds(20, 150, 800, 35);
		pnlButtonSave.setBackground(Color.gray);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Member Id");
		model.addColumn("Member Name");
		model.addColumn("ISBN");
		model.addColumn("Book Name");
		model.addColumn("Checkout Date");
		model.addColumn("Due Date");

		jt = new JTable(model);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(20, 200, 800, 150);
		panelCheckoutFields.add(sp);

		// Print CheckoutRecord

		panelCheckoutFields.add(lblMemberID);
		panelCheckoutFields.add(txtISBN);

		panelCheckoutFields.add(lblISBN);
		panelCheckoutFields.add(txtMemberID);

		panelCheckoutFields.add(btnCheckout);
		panelCheckoutFields.add(pnlButtonSave, BorderLayout.CENTER);

		this.setSize(450, 450);
		this.setVisible(true);
		this.add(panelCheckoutFields);

	}

	private void addCheckIDListener(JButton butn) {
		butn.addActionListener(evt -> {
			String bkISBN = txtISBN.getText().trim();
			String memberID = txtMemberID.getText().trim();

			if (bkISBN.length() == 0 || memberID.length() == 0) {
				JOptionPane.showMessageDialog(this, "ISBN and member ID required", "Save Failed",
						JOptionPane.ERROR_MESSAGE);
			} else {
					try {
						checkOutBookUseCase.checkOutBook(memberID, bkISBN);
						JOptionPane.showMessageDialog(this, "Checkout successful", "Thank you", JOptionPane.ERROR_MESSAGE);
						displayCheckoutInfo();
					} catch (BookNotFoundException | MemberNotFoundException | BooCopyNotAvailableException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Check out book", JOptionPane.ERROR_MESSAGE);
					}
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
			CheckOutBookUseCase checkOutBook = ControllerFactory.createCheckOutBookUseCase();

			JOptionPane.showMessageDialog(this, "Save successful");

			DefaultTableModel modell = (DefaultTableModel) jt.getModel();
			BookCopy bc = (BookCopy) cmbCopies.getSelectedItem();
			modell.addRow(new Object[] { txtMemberID.getText().trim(), LocalDate.now(),
					LocalDate.now().plusDays(bc.getBook().getMaxCheckoutLength()) });

			jt.setRowSelectionInterval(modell.getRowCount() - 1, modell.getRowCount() - 1);
			jt.scrollRectToVisible(new Rectangle(jt.getCellRect(modell.getRowCount() - 1, 0, true)));

			txtISBN.setText("");
			txtMemberID.setText("");
			cmbCopies.removeAllItems();
			idWasValidated = false;

		});
	}

	private void displayCheckoutInfo() {
		CheckOutRecord cr = checkOutBookUseCase.getCheckOutRecord(txtMemberID.getText());
		if (cr == null)
			return;

		DefaultTableModel model2 = (DefaultTableModel) jt.getModel();
		model2.setRowCount(0);
			
		for (CheckOutRecordEntry entry : cr.getCheckOutRecordEntries()) {
			model2.addRow(new Object[] { cr.getMember().getMemberId(), cr.getMember().getFullName(),
					entry.getBookCopy().getBook().getIsbn(),
					entry.getBookCopy().getBook().getTitle(), entry.getCheckOutDate().toString(),
					entry.getDueDate().toString() });

		}

	}

	public boolean isInitialized() {
		return this.isInitialized;
	}

	public void isInitialized(boolean val) {
		this.isInitialized = val;
	}



	@Override
	public void init() {
		checkOutBook();
	}

}
