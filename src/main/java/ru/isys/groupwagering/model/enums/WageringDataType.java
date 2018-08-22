package ru.isys.groupwagering.model.enums;

public enum WageringDataType {
    DATE("Спор на конкретную дату"),
    STRING("Ответ в виде строки символов"),
    NUMBER("Спор на конкретное число");

    private String description;

    private WageringDataType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[]args){
        for(WageringDataType s:WageringDataType.values())
            System.out.println(s+": "+s.getDescription());
    }
}
