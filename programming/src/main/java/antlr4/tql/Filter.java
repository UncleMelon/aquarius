package antlr4.tql;

public class Filter {
    private String column;
    private String refData;
    private String op;
    private String dataType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getRefData() {
        return refData;
    }

    public void setRefData(String refData) {
        this.refData = refData;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "column='" + column + '\'' +
                ", refData='" + refData + '\'' +
                ", op='" + op + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}