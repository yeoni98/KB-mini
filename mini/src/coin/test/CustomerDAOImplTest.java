package coin.test;

import java.sql.SQLException;
import java.util.ArrayList;

import coin.customer.CustomerDAOImpl;
import coin.vo.Customer;

public class CustomerDAOImplTest {
	public static void main(String[] args) throws SQLException  {
		CustomerDAOImpl dao = CustomerDAOImpl.getInstance();

//		//등록 테스트
//		try {
//			dao.addCustomer(new Customer("김연찬","yc", "1234","1111","010-1111-1111","수원"));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}

//		//삭제 테스트
//		try {
//			dao.removeCustomer(3);
//		}catch(SQLException e) {
//			System.out.println(e.getMessage());
//		}catch(RecordNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

//		//수정 테스트
//		try {
//			dao.updateCustomer(new Customer(2,"김연찬", "abc", "123", "1111", "010-1111-1111","수원"));
//		}catch(SQLException e) {
//			System.out.println(e.getMessage());
//		}catch(RecordNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		// 출력 테스트
		//전체   
//		ArrayList<Customer> customer = dao.getAllCustomers();
//	    for(Customer cs :customer) System.out.println(customer);
		
		
	       
	}
}