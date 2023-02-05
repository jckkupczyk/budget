//        Kod w mangement studio pomniżej:
//        use Adventureworks
//        go
//
//        select top 10 firstName, LastName from Person.Contact
//
//        use jkupczyk
//        go
//
//        select * from Forum.Topics
//        -- create schema budget;
//
//        create table budget.BudgetEntries (
//        ID int IDENTITY PRIMARY KEY,
//        EntryDate datetime NOT NULL DEFAULT getdate(),
//        EntryName nvarchar(255) NOT NULL,
//        Amount money NOT NULL,
//        )
//
//        -- drop table budget.BudgetEtries
//
//        select * from budget.BudgetEntries
//        delete from budget.BudgetEntries
//
//        --insert into budget.BudgetEntries(EntryName, Amount) values ('test', 0)
//
//        select sum(amount) from budget.BudgetEntries



package pl.edu.wszib.jwd.budget;

import java.math.BigDecimal;

public class BudgetEntry {
    private BigDecimal amount;
    private String entryName;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
}
