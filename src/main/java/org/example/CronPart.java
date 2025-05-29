package org.example;

import lombok.Data;

@Data
public class CronPart {

    private String text;
    private CronPartType type;

    public CronPart(String text, CronPartType type) throws IllegalArgumentException{
        this.text = text;
        this.type = type;

        parseStars(text, type);
        parseDash(text, type);
        parseComma(text, type);
    }

    private void parseDash(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.contains("-")){
            int dashIndex = cronPart.indexOf("-");
            int before =Integer.parseInt(cronPart.substring(0, dashIndex));
            int after = Integer.parseInt(cronPart.substring(dashIndex+1));

            buildRange(before, after, stringBuilder);
            System.out.println(type.toString()+ " : "+stringBuilder);
        }
    }

    private void parseComma(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.contains(",")){
            int dashIndex = cronPart.indexOf(",");
            int first =Integer.parseInt(cronPart.substring(0, dashIndex));
            int second = Integer.parseInt(cronPart.substring(dashIndex+1));

            buildValues(stringBuilder, first, second);
            System.out.println(type.toString()+ " : "+stringBuilder);
        }
    }

    private void parseStars(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.startsWith("*")){
            if (cronPart.length() ==1){
                buildRange(type.getMin(), type.getMax(), stringBuilder);
                System.out.println(type.toString()+ " : "+stringBuilder);

                // here would iterate through all possible value but need to indicate what the type is [Minutes, Hours, Day, ...]
                return;
            }

            int indexStar = cronPart.indexOf("*");

            if (String.valueOf(cronPart.charAt(indexStar + 1)).equals("/")){
                String substring = cronPart.substring(indexStar+2);
                // using type of expression, can see how many times substring fits in it

//                int intervals = max / Integer.valueOf(substring)

//                for(int i = 0; i < intervals; i++){
//                     add to string the value from interval
//                }
            }
        }
    }

    private void buildRange(int typeMin, int typeMax, StringBuilder stringBuilder){
        for(int min = typeMin; min <= typeMax; min++){
            stringBuilder.append(String.valueOf(min)).append(" ");
        }
    }

    private void buildValues(StringBuilder stringBuilder, int... values){
        for (int value : values){
            stringBuilder.append(value).append(" ");
        }
    }
}