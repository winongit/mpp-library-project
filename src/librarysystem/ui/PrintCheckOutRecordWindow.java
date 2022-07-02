package librarysystem.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.time.LocalDate;

import javax.swing.JButton;
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
import business.usecase.CheckOutBookUseCase;
import domain.BookCopy;
import domain.CheckOutRecord;
import domain.CheckOutRecordEntry;

public class PrintCheckOutRecordWindow extends JFrame implements LibWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final PrintCheckOutRecordWindow INSTANCE = new PrintCheckOutRecordWindow();

	private PrintCheckOutRecordWindow() {
	}

	CheckOutBookUseCase checkOutBookUseCase = ControllerFactory.createCheckOutBookUseCase();
	private boolean isInitialized = false;

	JTextField txtMemberID;
	JTable jt;
	
	private boolean idWasValidated = false;

	public void checkOutBook() {

		JPanel panelCheckoutFields = new JPanel();
		panelCheckoutFields.setLayout(null);

		this.setTitle("Print Checkout Records");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setBounds(20, 50, 100, 20);
		txtMemberID = new JTextField(10);
		txtMemberID.setBounds(110, 50, 100, 20);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(110, 80, 100, 20);
		addCheckIDListener(btnSearch);

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
		panelCheckoutFields.add(txtMemberID);

		panelCheckoutFields.add(btnSearch);
		panelCheckoutFields.add(pnlButtonSave, BorderLayout.CENTER);

		this.setSize(450, 450);
		this.setVisible(true);
		this.add(panelCheckoutFields);

	}

	private void addCheckIDListener(JButton butn) {
		butn.addActionListener(evt -> {
			String memberID = txtMemberID.getText().trim();

			if (memberID.length() == 0) {
				JOptionPane.showMessageDialog(this, "Member ID required", "Search Failed",
						JOptionPane.ERROR_MESSAGE);
			} else {
					try {
						displayCheckoutInfo();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Search Failed!", JOptionPane.ERROR_MESSAGE);
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

//	private void addCheckoutBtnListener(JButton butn) {
//		butn.addActionListener(evt -> {
//
//			if (!idWasValidated) {
//				JOptionPane.showMessageDialog(this, "Enter valid ID then click search again.", "Search Failed",
//						JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			CheckOutBookUseCase checkOutBook = ControllerFactory.createCheckOutBookUseCase();
//
//			DefaultTableModel modell = (DefaultTableModel) jt.getModel();
//			//BookCopy bc = (BookCopy) cmbCopies.getSelectedItem();
//			modell.addRow(new Object[] { txtMemberID.getText().trim(), LocalDate.now(),
//					LocalDate.now().plusDays(bc.getBook().getMaxCheckoutLength()) });
//
//			jt.setRowSelectionInterval(modell.getRowCount() - 1, modell.getRowCount() - 1);
//			jt.scrollRectToVisible(new Rectangle(jt.getCellRect(modell.getRowCount() - 1, 0, true)));
//
//			txtMemberID.setText("");
//			cmbCopies.removeAllItems();
//			idWasValidated = false;
//
//		});
//	}

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
