package librarysystem.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.print.Book;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.impl.ControllerFactory;
import business.usecase.AddBookCopyUseCase;
import business.usecase.CheckBookCopyAvailableUseCase;
import business.usecase.CheckOutBookUseCase;
import domain.exception.BookNotFoundException;



public class CreateBookCopy extends JFrame {

	public static final CreateBookCopy INSTANCE = new CreateBookCopy();
	private boolean isInitialized = false;
	
	CheckBookCopyAvailableUseCase checkBookCopyAvailableUseCase = ControllerFactory.createCheckBookCopyAvailableUseCase();
	AddBookCopyUseCase addBookCopyUseCase = ControllerFactory.createAddBookCopyUseCase();
	
	private JTextField txtISBN, txtCopyNumber;

	public void addCopy() {
		JPanel panelCreateCopyField = new JPanel();
		panelCreateCopyField.setLayout(null);
		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setBounds(20,20,100,20);
		
		txtISBN = new JTextField(10);
		txtISBN.setBounds(110,20,100,20);
		
		JLabel lblCopyNumber = new JLabel("Copy Number:");
		lblCopyNumber.setBounds(20,50,100,20);
		
		txtCopyNumber = new JTextField(10);
		txtCopyNumber.setBounds(110,50,100,20);
		
		JPanel pnlButtonSave = new JPanel();
		
		JButton btnSave = new JButton("Save");
		addCreateCopyButtonListener(btnSave) ;
		
		JButton btnBacktoMain = new JButton("<< Back to Main");
		addBackButtonListener(btnBacktoMain);
		
		pnlButtonSave.add(btnBacktoMain);
		pnlButtonSave.add(btnSave);
		pnlButtonSave.setBounds(20, 100, 360, 35);
		pnlButtonSave.setBackground(Color.green);
		
		panelCreateCopyField.add(lblISBN);
		panelCreateCopyField.add(txtISBN);
		
		panelCreateCopyField.add(lblCopyNumber);
		panelCreateCopyField.add(txtCopyNumber);
		
		panelCreateCopyField.add(pnlButtonSave, BorderLayout.CENTER);
		
		
		 this.setTitle("Create Book Copy");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//this.setLayout(null);
			this.setSize(430,230);
			this.setVisible(true);
			this.add(panelCreateCopyField);
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}
	
	private void addCreateCopyButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			
			if(txtISBN.getText().equals("") || txtCopyNumber.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Required Fields can not be left empty","Save Failed", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				domain.Book book1 = checkBookCopyAvailableUseCase.checkBookAvailableCopy(txtISBN.getText());
				addBookCopyUseCase.addBookCopy(book1);
				
			} catch (BookNotFoundException e) {
				JOptionPane.showMessageDialog(this,e.getMessage(),"Save Failed", JOptionPane.ERROR_MESSAGE);
			}
			
			txtISBN.setText("");
			txtCopyNumber.setText(getName());
			
			JOptionPane.showMessageDialog(this,"Save successful");	
		});
	}
	

	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}


	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		
	}

}
