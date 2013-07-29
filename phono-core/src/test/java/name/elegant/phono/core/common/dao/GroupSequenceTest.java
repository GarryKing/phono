package name.elegant.phono.core.common.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/springMVCForm-servlet.xml" })
public class GroupSequenceTest {

    @Test
    public void testInsert() {
        System.out.println("hi");
    }
}
