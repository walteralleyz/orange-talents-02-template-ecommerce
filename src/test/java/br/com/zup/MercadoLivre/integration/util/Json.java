package br.com.zup.MercadoLivre.integration.util;

public class Json {
    private StringBuilder json;

    public Json() {
        setup();
    }

    private void setup() {
        json = new StringBuilder();
        json.append("{");
    }

    private void close(StringBuilder s, String c) {
        int index = s.lastIndexOf(",");
        s.deleteCharAt(index);
        s.append(c);
    }

    public Json property(String field, String attribute) {
        json.append(String.format("\"%s\": \"%s\",", field, attribute));
        return this;
    }

    public Json property(String field, Integer attribute) {
        json.append(String.format("\"%s\": %d,", field, attribute));
        return this;
    }

    public Json property(String field, String... attribute) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");

        for(String attr : attribute) {
            Json json = new Json();

            String[] splatted = attr.split(",");

            for(String s : splatted) {
                String[] property = s.split(":");
                json.property(property[0], property[1]);
            }

            temp.append(json.compact());
            temp.append(",");
        }

        close(temp, "]");

        json.append(String.format("\"%s\": %s,", field, temp));
        return this;
    }

    public String compact() {
        close(json, "}");
        String temp = json.toString();
        setup();

        return temp;
    }
}
