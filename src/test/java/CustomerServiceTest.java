import org.apache.demo.model.Customer;
import org.apache.demo.service.CustomerService;
import org.junit.Test;

import java.util.List;

/**
 * 单元测试
 */
public class CustomerServiceTest {
    private final CustomerService customerService;
    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    /*@Before
    public void init() throws Exception{

        DataBaseHelper.executeSqlFile("sql/customer_init.sql");
    }*/
    /**
     *
     * 获取客户列表清单
     */
    @Test
    public void getCustomerListTest() throws Exception{
        List<Customer> customerList = customerService.getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i).toString());
        }
    }

}
