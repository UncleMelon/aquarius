package json;

import com.alibaba.fastjson.JSONPath;

import java.util.List;

/**
 * case by case，能pojo就不json；一定要嵌套取就预置一个jsonpath
 * 不得不搞的话也尽量把这种脏活藏在一个可替换的小组件里
 * 多一个产出pojo的adaptor也好
 * 我会用Fastjson或JsonPath的parse方法，还可以传TypeRef实例
 * 如val typeRef = new TypeRef<List<User>>() {
 * };
 * TypeReference这种能做到最好
 * 不过类似订单标或者商品属性这种，设计的时候可能会留下很多Legacy
 * @author matthew_wu
 * @since 2019-08-14 10:31
 */
public class JsonPathTest {

    public static void main(String[] args) {
        String json = "{\"store\":{\"book\":[{\"title\":\"高效Java\",\"price\":10},{\"title\":\"研磨设计模式\",\"price\":12},{\"title\":\"重构\",\"isbn\":\"553\",\"price\":8},{\"title\":\"虚拟机\",\"isbn\":\"395\",\"price\":22}],\"bicycle\":{\"color\":\"red\",\"price\":19}}}";
        //获取json中store下book下的所有title值
        List<String> titles = (List<String>) JSONPath.read(json, "$.store.book.title");
        System.out.println("$.store.book.title =" + titles);

        //获取json中所有title的值
        titles = (List<String>) JSONPath.read(json, "$..title");
        System.out.println("$..title = " + titles);

        //获取json中book数组包含isbn的所有值
        List<Object> isbns = (List<Object>) JSONPath.read(json, "$.store.book[?(@.isbn)]");
        System.out.println("$.store.book[?(@.isbn)] = " + isbns);

        //获取json中book数组中price<10的所有值
        List<Object> prices = (List<Object>) JSONPath.read(json, "$.store.book[?(@.price < 10)]");
        System.out.println("$.store.book[?(@.price < 10)] = " + prices);

        //获取json中book数组中的title等于"高效Java"的对象
        titles = (List<String>) JSONPath.read(json, "$.store.book[?(@.title = '高效Java')]");
        System.out.println("$.store.book[?(@.title = '高效Java')] = " + titles);

        //获取json中store下所有price的值
        prices = (List<Object>) JSONPath.read(json, "$.store..price");
        System.out.println("$.store..price = " + prices);

        //获取json中book数组的前两个区间值
        List<Object> books = (List<Object>) JSONPath.read(json, "$.store.book[:2]");
        System.out.println("$.store.book[:2] = " + books);

        //获取书个数
        int size = (int) JSONPath.read(json, "$.store.book.size()");
        System.out.println("$.store.book.size() = " + size);
    }
}


/*{
    "store": {
        "book": [
            {
            "title": "高效Java",
            "price": 10
            },
            {
            "title": "研磨设计模式",
            "price": 12
            },
            {
            "title": "重构",
            "isbn": "553",
            "price": 8
            },
            {
            "title": "虚拟机",
            "isbn": "395",
            "price": 22
            }
            ],
        "bicycle": {
        "color": "red",
        "price": 19
    }
    }
}*/
