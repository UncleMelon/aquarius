package oo.overloading;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author matthew_wu
 * @Description  Function with generic list parameter... (Overloading function)
 * @Date 11:41 2019-01-18
 * @Param 
 * @return 
 **/
public class FunctionOverLoading {

    public static <T> void test(List<T> objects) {
        if (objects == null || objects.size() == 0) {
            System.out.println("no data");
        }
        if (objects.get(0) instanceof List ) {
            System.out.println("this is function test1 with parameter List<List<Object>>");
        } else if (objects.get(0) instanceof String) {
            System.out.println("this is function test2 with parameter List<String>");
        }
    }

    public static void main(String[] args) {
        List<Object> subList = new ArrayList<Object>() {{ add(1); }};
        List<List<Object>> priList = new ArrayList<>();
        priList.add(subList);
        test(priList);

        List<String> testList = new ArrayList<String>(){{add("testewqtaset"); }};
        test(testList);
    }

    public void test1(List<List<Object>> result) {
        System.out.println("this is function test1 with parameter List<List<Object>>");
    }

    public void test2(List<String> result) {
        System.out.println("this is function test2 with parameter List<String>");
    }
    
}
