package com.revature.repositories;

import java.io.CharArrayReader;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionBank;

public class AccountImplementationPostgreDAO implements AccountDAO {

	private ConnectionBank cb = ConnectionBank.getConnectionBank();
	
	public Account createAccount(Account ac) {
		Connection conn = cb.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql = "insert into account (pw, fName, lName, accType) "
						+ "values(?, ?, ?, ?) returning main_id;";
			
			PreparedStatement insertAcc = conn.prepareStatement(sql);
			
			insertAcc.setCharacterStream(1, new CharArrayReader(ac.getPassword()));
			insertAcc.setString(2, ac.getfName());
			insertAcc.setString(3, ac.getlName());
			insertAcc.setString(4, String.valueOf(ac.getType()));
			
			ResultSet res = insertAcc.executeQuery();
			
			int newId;
			if (res.next()) {
				newId = res.getInt("main_id");
			} else {
				throw new SQLException();
			}
			
			ac.setId(newId);
			if(ac.getClass() == Customer.class) {
				Customer cs = (Customer) ac;
				String custSql = "insert into customer (main_id, balance, status) "
								+ "values(?, ?, ?);";
				PreparedStatement insertCust = conn.prepareStatement(custSql);
				insertCust.setInt(1, newId);
				insertCust.setDouble(2, cs.getBalance());
				insertCust.setString(3, String.valueOf(cs.getStatus()));
				
				insertCust.executeUpdate();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cb.releaseConnection(conn);
		}
		
		return ac;
	}

	public Account updateAccount(Customer ac, double amount) {
		Connection conn = cb.getConnection();
		
		
		return ac;
	}

	public List<Account> showAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> showAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> showAllInactiveCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account findAccountById(int id, char type) {
		// TODO Auto-generated method stub
		return null;
	}

	public char findCustomerStatusById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
