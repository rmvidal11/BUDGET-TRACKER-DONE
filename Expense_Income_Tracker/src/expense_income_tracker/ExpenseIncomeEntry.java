

package expense_income_tracker;

/**
 * 
 * @mia
*/

   /*
* ExpenseIncomeEntry represents a single entry for expenses and income.
* Each entry contains a date, description,amount and type (expense or income
*/

public class ExpenseIncomeEntry{

    private final String date;
    private final String description;
    private final double amount;
    // the type of the entry (expense or income)



    public ExpenseIncomeEntry(String date, String description, double amount, String type)
    {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public String getDate(){ return date;}
    public String getDescription(){ return description;}
    public double getAmount(){ return amount;}
    public String getType(){ return date;}




    }