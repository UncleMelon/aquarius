package java8.charpter2;


public class Apple {

    private String color;

    private Integer weight;

    private Double price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Apple(String color, Integer weight, Double price) {
        this.color = color;
        this.weight = weight;
        this.price = price;
    }
}
