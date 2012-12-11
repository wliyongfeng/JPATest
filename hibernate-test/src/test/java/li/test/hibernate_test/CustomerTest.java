package li.test.hibernate_test;

import li.test.hibernate.Customer;
import li.test.hibernate.CustomerDao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
    CustomerDao dao;
    
    @Before
    public void setUp() {
        dao = new CustomerDao();
    }
    
    @Test
    public void testInsert() {
        Customer customer = new Customer(3, "ziyongfeng");
        dao.insert(customer);
        assertThat(dao.findAll().size(), equalTo(1));
    }

}
