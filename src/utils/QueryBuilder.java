package utils;

public class QueryBuilder {
    private StringBuilder stringBuilder;

    public QueryBuilder () {
        this(new StringBuilder());
    }

    public QueryBuilder (StringBuilder builder) {
        this.stringBuilder = builder;
    }

   /* public QueryBuilder select (String [] columns, String tableName) {
        String query = "SELECT ";
    }*/
}
