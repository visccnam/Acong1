package acong.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import acong.entities.Customer;
import acong.entities.CustomerMapper;

@Repository
@Transactional
public class CustomerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void save(Customer customer) {
	    String sql = "INSERT INTO customer (name, address) VALUES (?, ?)";
	    jdbcTemplate.update(sql, customer.getName(), customer.getAddress());
	  }
	public void addMany(List<Customer> list) {
		String sql="INSERT INTO user_info (name, address) VALUES (?, ?)";
		jdbcTemplate.batchUpdate(sql, (BatchPreparedStatementSetter) list);
	}
	  public void delete(int id) {
	    String sql = "DELETE FROM customer WHERE id = " + id;
	    jdbcTemplate.update(sql);
	  }
	  
	  public void update(Customer customer) {
	    String sql = "UPDATE customer SET name = ?, address = ? WHERE id = ? ";
	    jdbcTemplate.update(sql, customer.getName(), customer.getAddress(), customer.getId());
	  }
	  public Customer findById(int id) {
	    String sql = "SELECT * FROM customer WHERE id = ?";
	    return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
	  }
	  public List<Customer> findAll() {
	    String sql = "SELECT * FROM customer";
	    return jdbcTemplate.query(sql, new CustomerMapper());
	  }

}
