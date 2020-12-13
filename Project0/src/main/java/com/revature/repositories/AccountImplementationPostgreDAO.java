package com.revature.repositories;

import java.io.CharArrayReader;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.jdbc.PgArray;

import com.revature.launcher.AccountLauncher;
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
			AccountLauncher.e720Logger.debug("ACCOUNT CREATION IS FAILED");
			e.printStackTrace();
			try {
				//System.out.println("Roll Back");
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				//System.out.println("Commit");
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
		try {
			conn.setAutoCommit(false);
			
			String sql = "update customer " +
						 "set balance = ? " + 
						 "where main_id = ? ;";
			PreparedStatement updateAcc = conn.prepareStatement(sql);
			
			updateAcc.setDouble(1, amount);
			updateAcc.setInt(2, ac.getId());
			
			updateAcc.executeUpdate();
			
		}catch (SQLException e) {
			AccountLauncher.e720Logger.debug("ACCOUNT UPDATE IS FAILED");
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
				e.printStackTrace();
			}
			cb.releaseConnection(conn);
		}
		
		return ac;
	}
	
	public Customer updateCustStatus(Customer ac, char status) {
		Connection conn = cb.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String sql = "update customer " +
						 "set status = ? " + 
						 "where main_id = ? ;";
			PreparedStatement updateAcc = conn.prepareStatement(sql);
			
			updateAcc.setString(1, Character.toString(status));
			updateAcc.setInt(2, ac.getId());
			
			updateAcc.executeUpdate();
			
			
		}catch (SQLException e) {
			AccountLauncher.e720Logger.debug("ACCOUNT_STATUS UPDATE IS FAILED");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cb.releaseConnection(conn);
		}
		return ac;
	}


	public List<Account> showAllAccount() {
		Connection conn = this.cb.getConnection();
		List<Account> all = new ArrayList<Account>();

		try {
			String sql = "select * from account;";
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			while (res.next()) {

				Account ac = new Account(res.getInt("main_id"), res.getString("pw").toCharArray());
				ac.setfName(res.getString("fName"));
				ac.setlName(res.getString("lName"));
				ac.setType(res.getString("accType").charAt(0));
				all.add(ac);
			}

		} catch (SQLException e) {
			AccountLauncher.e720Logger.debug("ACCOUNT LISTS CANNOT BE CALLED");
			e.printStackTrace();
		} finally {
			cb.releaseConnection(conn);
		}
		return all;
	}

	public List<Customer> showAllCustomers() {
		Connection conn = this.cb.getConnection();
		List<Customer> allCust = new ArrayList<Customer>();

		try {
			String sql = "select a.main_id, a.pw, a.fname, a.lname, c.balance, c.status "
					+ "from account a "
					+ "join customer c "
					+ "on a.main_id = c.main_id "
					+ "where a.acctype = 'C'; ";
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			while (res.next()) {
				//(int inId, char[] password ,String inFName, String inLName, double inBalance, char status)
				Customer cst = new Customer(res.getInt("main_id"), res.getString("pw").toCharArray(), 
											res.getString("fName"), res.getString("lName"),
											res.getDouble("balance"), res.getString("status").charAt(0));
				allCust.add(cst);
			}

		} catch (SQLException e) {
			AccountLauncher.e720Logger.debug("ACCOUNT_CUSTPMER LISTS CANNOT BE CALLED");
			e.printStackTrace();
		} finally {
			cb.releaseConnection(conn);
		}
		AccountLauncher.e720Logger.info(allCust);
		return allCust;
	}

	public List<Customer> showAllInactiveCustomers() {
		Connection conn = this.cb.getConnection();
		List<Customer> cstInactList = new ArrayList<Customer>();
		try {
			String sql = "select a.main_id, a.pw, a.fname, a.lname, c.balance, c.status "
					+ "from account a "
					+ "join customer c "
					+ "on a.main_id = c.main_id "
					+ "where a.acctype = 'C' and c.status= 'I'; ";
			Statement s = conn.createStatement();

			ResultSet res = s.executeQuery(sql);
			while (res.next()) {
				//(int inId, char[] password ,String inFName, String inLName, double inBalance, char status)
				Customer cst = new Customer(res.getInt("main_id"), res.getString("pw").toCharArray(), 
											res.getString("fName"), res.getString("lName"),
											res.getDouble("balance"), res.getString("status").charAt(0));
				cstInactList.add(cst);
			}

		} catch (SQLException e) {
			AccountLauncher.e720Logger.debug("ACCOUNT_ACTIVE_CUSTOMER LISTS CANNOT BE CALLED");
			e.printStackTrace();
		} finally {
			cb.releaseConnection(conn);
		}
		AccountLauncher.e720Logger.info(cstInactList);
		return cstInactList;
	}

	public Account findAccountById(int id, char type) {
		Connection conn = this.cb.getConnection();
		Account ac = null;
		try {
			//System.out.println("id :" + id + ", type:" + type);
			String sql = "";
			if(type == 'C') {
				sql = "select a.main_id, a.pw, a.fname, a.lname, c.balance, c.status "
						+ "from account a "
						+ "join customer c "
						+ "on a.main_id = c.main_id "
						+ "where a.main_id = " + id +" and a.acctype = \'" + type + "\';";
			}else {
				sql = "select * from project0.account "+
						 "where main_id = " + id +" and acctype = \'" + type + "\';";
			}
			

			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			if(res.next()) {
				//inId, char[] password ,String inFName, String inLName, double inBalance, char status
				if(type == 'C') {
					ac = new Customer(res.getInt("main_id"), res.getString("pw").toCharArray(), 
							res.getString("fName"), res.getString("lName"),
							res.getDouble("balance"), res.getString("status").charAt(0));
				}else {
					ac = new Account(res.getInt("main_id"), res.getString("pw").toCharArray(), 
							res.getString("fName"), res.getString("lName"),
							res.getString("acctype").charAt(0));
					return ac;
				}
			}
			
		} catch (SQLException e) {
			AccountLauncher.e720Logger.debug("FINDING ACCOUNT IS FAILED");
			e.printStackTrace();
		} finally {
			cb.releaseConnection(conn);
		}
		return ac;
	}

	public char findCustomerStatusById(int id) {
		char status = 'I';
		Connection conn = this.cb.getConnection();
		
		try {
			String sql = "select status from customer "+
						 "where main_id = " + id +" ;" ;
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			if(res.next()) {
				status = res.getString("status").charAt(0);
				return status;
			}
		} catch (SQLException e) {
			AccountLauncher.e720Logger.debug("FINDING ACCOUNT_CUSTOMER IS FAILED");
			e.printStackTrace();
		} finally {
			cb.releaseConnection(conn);
		}
		return status;
	}
}
