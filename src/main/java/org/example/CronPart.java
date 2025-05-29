package org.example;

import lombok.Data;

@Data
public class CronPart {

    private String text;
    private CronPartType type;
    private String expandedText;

    public CronPart(String text, CronPartType type) throws IllegalArgumentException{
        this.text = text;
        this.type = type;
        this.expandedText = parseText(type, text);

    }

    public String parseText(CronPartType type, String text){
        StringBuilder result = new StringBuilder();
        result.append(parseStars(text, type));
        result.append(parseDash(text));
        result.append(parseComma(text));
        result.append(parseInteger(text));

        return result.toString().trim();
    }

    private String parseInteger(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();

        if (!cronPart.contains("-") && !cronPart.contains("*") && !cronPart.contains("/") && !cronPart.contains(",")){
            stringBuilder.append(cronPart).append(" ");
            System.out.println(type.toString()+ " : "+cronPart);
        }
        return stringBuilder.toString();
    }

    private String parseDash(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.contains("-")){
            int dashIndex = cronPart.indexOf("-");
            int before =Integer.parseInt(cronPart.substring(0, dashIndex));
            int after = Integer.parseInt(cronPart.substring(dashIndex+1));

            buildRange(before, after, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private String parseComma(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.contains(",")){
            int dashIndex = cronPart.indexOf(",");
            int first =Integer.parseInt(cronPart.substring(0, dashIndex));
            int second = Integer.parseInt(cronPart.substring(dashIndex+1));

            buildValues(stringBuilder, first, second);
        }
        return  stringBuilder.toString();
    }

    private String parseStars(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.startsWith("*")){
            if (cronPart.length() == 1){
                buildRange(type.getMin(), type.getMax(), stringBuilder);
            } else {int indexStar = cronPart.indexOf("*");

                if (String.valueOf(cronPart.charAt(indexStar + 1)).equals("/")){
                    buildInterval(cronPart, indexStar, stringBuilder);}
            }
        }
        return stringBuilder.toString();
    }

    private void buildRange(int typeMin, int typeMax, StringBuilder stringBuilder){
        for(int min = typeMin; min <= typeMax; min++){
            stringBuilder.append(min).append(" ");
        }
    }

    private void buildValues(StringBuilder stringBuilder, int... values){
        for (int value : values){
            stringBuilder.append(value).append(" ");
        }
    }

    private void buildInterval(String cronPart, int indexStar, StringBuilder stringBuilder){
        String substring = cronPart.substring(indexStar+2);
        int interval = Integer.parseInt(substring);
        int amount = type.getMax() /interval;

        for (int i = 0; i <= amount; i++){
            int appendedCronValue = interval * i;
            stringBuilder.append(appendedCronValue).append(" ");
        }
    }
}