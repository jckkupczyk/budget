package pl.edu.wszib.jwd.budget;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;

public class HomeBudget {
    //TODO: do innego pliku lub miejsca poza repo!!!
    private static final String DB_USER = "jkupczyk";
    private static final String DB_PASS = "******";

    private static final String SQL_INSERT_ENTRY = "insert into budget.BudgetEntries(EntryName, Amount) values (?, ?)";
    private static final String SQL_GET_BALANCE = "select sum(amount) as balance from budget.BudgetEntries";

    public static void main(String[] args) {
        HomeBudget hb = new HomeBudget();
        BudgetEntry be = new BudgetEntry();

        //TODO: potrzebna walidacja
        be.setEntryName(args[0]);
        be.setAmount(new BigDecimal(args[1]));



        try (Connection con = hb.connect(DB_USER, DB_PASS);
             Statement stmt = con.createStatement();

        ) {

            PreparedStatement ps = con.prepareStatement(SQL_INSERT_ENTRY);
            ps.setString(1, be.getEntryName());
            ps.setBigDecimal(2, be.getAmount());
            ps.execute();
            ps.close();

            BigDecimal balance;
            ResultSet rs = stmt.executeQuery(SQL_GET_BALANCE);
            if (rs.next()) {
                balance = rs.getBigDecimal("balance");
            } else {
                balance = new BigDecimal(0);
            }

            System.out.print("Zapisano! kwota: " + currencyFormat(be.getAmount()));
            System.out.print(", nazwa: " + be.getEntryName());
            System.out.println(", saldo: " + currencyFormat(balance));

        } catch (SQLException e) {
            System.out.println("problemy z bazą danych: " + e.getMessage());
        }

    }

    Connection connect(String username, String password) throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(username);
        ds.setPassword(password);
        ds.setDatabaseName(username);

        ds.setServerName("morfeusz.wszib.edu.pl");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);

        return ds.getConnection();
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);

    }
}
