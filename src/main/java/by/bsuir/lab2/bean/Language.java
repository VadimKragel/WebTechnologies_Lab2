package by.bsuir.lab2.bean;

public enum Language {
    RU("ru"), EN("en");

    private final String stringValue;

    Language(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
