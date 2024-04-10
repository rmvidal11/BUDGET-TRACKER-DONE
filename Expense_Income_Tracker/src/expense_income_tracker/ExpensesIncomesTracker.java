/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expense_income_tracker;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.JTable;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
/**
 *
 * @author mia
 */
//Constructor to iniatialize the application and set up the foam
public class ExpensesIncomesTracker extends JFrame{

    private final ExpenseIncomeTableModel tableModel;
    private final JTable table;

    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String>typeCombobox;
    private final JButton addButton; 
    private final JLabel balanceLabel;
    private double balance;
    private final JTextField dateField;

   public ExpensesIncomesTracker(){

       try{
           //Apply the FlatDarkLaf look and feel for a modern and flat appearance.
        UIManager.setLookAndFeel(new FlatDarkLaf());
   }
   catch(UnsupportedLookAndFeelException ex){
       System.err.println("Failed to Set FlatDarkLaf LookAndFeel");
   }
       //Custom color schemes for specific swing components
      UIManager.put("TextField.foreground", Color.WHITE);
      UIManager.put("TextField.background", Color.DARK_GRAY);
      UIManager.put("TextField.caretForeground", Color.RED);
      UIManager.put("ComboBox.forground", Color.YELLOW);
      UIManager.put("ComboBox.selectionForeground", Color.WHITE);
      UIManager.put("ComboBox.background", Color.BLACK);
      UIManager.put("Button.foreground", Color.WHITE);
      UIManager.put("Button.background", Color.ORANGE);
      UIManager.put("Label.foreground", Color.WHITE);
      
      
      //Set the default font for the entire application
      Font customFont = new Font("Arial", Font.PLAIN, 18);
      UIManager.put("Label.font", customFont);
      UIManager.put("TextField.font", customFont);
      UIManager.put("ComboBox.font", customFont);
      UIManager.put("Button.font", customFont);
            
      
      //Initialize the table model and balance variable.
      amountField = new JTextField(10);
      balance = 0.0;
      tableModel = new ExpenseIncomeTableModel();
      
      //Create a JTable and set up a scroll pane to display tha data.
      table = new JTable(tableModel);
      JScrollPane scrollpane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
      
      //Create input fields and components for adding new entries.
      dateField = new JTextField(10);
      descriptionField = new JTextField(20);
      typeCombobox = new JComboBox<>(new String[]{"Expense", "Income"});
      
      //Attach an ActionListener to the "Add" button to handle new entry addition.
      addButton = new JButton("Add");
      addButton.addActionListener(e -> addEntry());
      balanceLabel = new JLabel("Balance: $" + balance);
      
      //Create input panel to arrange input components
      JPanel inputPanel = new JPanel();
      inputPanel.add(new JLabel("Date"));
      inputPanel.add(dateField);

      inputPanel.add(new JLabel("Description"));
      inputPanel.add(descriptionField);

      inputPanel.add(new JLabel("Amount"));
      inputPanel.add(amountField);

      inputPanel.add(new JLabel("Type"));
      inputPanel.add(typeCombobox);

      inputPanel.add(addButton);
      
      //Create bottom panel to display tha balance.
      JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      bottomPanel.add(balanceLabel);
      setLayout(new BorderLayout());
      
      //Set the layout of the main frame and add components to appropriate positions.
      add(inputPanel, BorderLayout.NORTH);
      add(scrollpane, BorderLayout.CENTER);
      add (bottomPanel, BorderLayout.SOUTH);
      
      //Set the title, default close operation, and visibility of the main frame.
      setTitle("Expenses And Incomes Tracker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
      
   }
   //Method to handle adding new entries to the table
   private void addEntry()
   {
       
     //Get input values from input fields.
      String date = dateField.getText();
      String description = descriptionField.getText();
      String amountStr = amountField.getText();
      String type = (String) typeCombobox.getSelectedItem();
      double amount;
      
      // Validate input values.
      // you can the description and date to the validation
      if(amountStr.isEmpty())
   {
     JOptionPane.showMessageDialog(this,"Enter the Amount", "Error", JOptionPane.ERROR_MESSAGE);  
     return;
   }
   try 
   {
       amount = Double.parseDouble(amountStr);
   }
       catch(NumberFormatException ex)
   {
           JOption.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.ERROR_MESSAGE);
           return;
   }
      if(type.equals("Expense"))
      {
          amount *= -1;
      }
      ExpenseIncomeEntry entry = new ExpenseIncomeEntry(date, description, amount, type);
      tableModel.addEntry(entry);
      balance += amount;
      balanceLabel.setText("Balance: $"+ balance);
      
      clearInputFields();   
   }
   
      
    private void clearInputFields()
{
    dateField.setText("");
    descriptionField.setText("");
    amountField.setText("");
    typeCombobox.setSelectedIndex(0);;

}}