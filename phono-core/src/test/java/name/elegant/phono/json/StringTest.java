package name.elegant.phono.json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Author: Garry King
 * Date: 13-7-29 下午10:55
 * E-mail:flyhzq@sina.com
 */
public class StringTest {

    private static Random random = new Random();

    private List<PersonString> testList = generateThousandPerson();

    private List<String> testList_de = generateThousandPersonString();

    private static Gson gson = new Gson();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final int TIMES = 10000 * 10;


    static {
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
//        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    }


    @Test
    public void testEnviroment() {
        System.out.println("===================");
        System.out.println("  CODE=200 , isOk  ");
        System.out.println("===================");
    }

    @Test
    public void testCode() throws IOException {
        int type = 2;
        testGson(type);
        testJackson(type);
        testFastJson(type);
    }

    private void testGson(int type) {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    gson.toJson(testList.get(j));
                else
                    gson.fromJson(testList_de.get(j), PersonString.class);
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
                    objectMapper.readValue(testList_de.get(j), PersonString.class);
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
                    JSON.parseObject(testList_de.get(j), PersonString.class);
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

    private List<PersonString> generateThousandPerson() {
        List<PersonString> list = new ArrayList<PersonString>();
        for (int i = 0; i < 1000; i++) {
            list.add(generatePerson());
        }
        return list;
    }

    private List<String> generateThousandPersonString() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            list.add(generatePersonString());
        }
        return list;
    }

    private String generatePersonString() {
        return "{\"name\":\"张55\",\"address\":\"浙江省杭州市文一路77号\",\"sex\":\"b\",\"postCode\":\"-6457373387768980607\"}";
    }

    private PersonString generatePerson() {
        PersonString person = new PersonString();
        person.setName("张" + random.nextInt(100));
        person.setAddress("浙江省杭州市文一路" + random.nextInt(100) + "号");
        person.setPostCode("" + random.nextLong());
        person.setSex(random.nextInt(100) > 49 ? 'b' : 'g');
        return person;
    }

}

class PersonString {
    private String name;
    private String address;
    private char sex;
    private String postCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

