package name.elegant.phono.json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Author: Garry King
 * Date: 13-8-1 ÉÏÎç1:42
 * E-mail:flyhzq@sina.com
 */
public class GenericityTest {

    private static Random random = new Random();

    private List<PersonGenericity> testList = generateThousandPerson();

    private List<PersonGenericity> generateThousandPerson() {
        List<PersonGenericity> list = new ArrayList<PersonGenericity>();
        for (int i = 0; i < 1000; i++) {
            PersonNumber person = new PersonNumber();
            person.setAge(random.nextInt(100));
            person.setHeight(random.nextDouble() + 1);
            person.setBirth(System.currentTimeMillis());
            person.setCardId(random.nextLong());
            list.add(new PersonGenericity(person));
        }
        return list;
    }

    private static Gson gson = new Gson();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final int TIMES = 1000 * 10;

    @Test
    public void testCode() throws IOException {
        int type = 2;
//        testGson(type);
//        testJackson(type);
        testFastJson(type);
    }

    private void testGson(int type) {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    gson.toJson(testList.get(j));
                else
                    gson.fromJson(gson.toJson(testList.get(j)), PersonGenericity.class);
//                System.out.println(gson.toJson(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("gson", before, after);
    }

    private void testJackson(int type) throws IOException {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    objectMapper.writeValueAsString(testList.get(j));
                else
                    objectMapper.readValue(objectMapper.writeValueAsString(testList.get(j)), PersonGenericity.class);
//                System.out.println(objectMapper.writeValueAsString(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("jackson", before, after);
    }

    private void testFastJson(int type) {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    JSON.toJSONString(testList.get(j));
                else
                    JSON.parseObject(JSON.toJSONString(testList.get(j)), PersonGenericity.class);
//                System.out.println(JSON.toJSONString(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("fastjson", before, after);
    }

    private void showTimes(String type, long before, long after) {
        double diff = after - before;
        diff = diff / 1000;
        System.out.println(type + " used:" + diff + " seconds");
    }

    class PersonGenericity {

        private ArrayList<PersonNumber> list;

        public PersonGenericity(PersonNumber object) {
            list = new ArrayList<PersonNumber>();
            list.add(object);
        }

        public ArrayList<PersonNumber> getList() {
            return list;
        }

        public void setList(ArrayList<PersonNumber> list) {
            this.list = list;
        }
    }

}

