package org.task;

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
        if (text.startsWith("*")){
            result.append(parseStars(text, type));
        } else if (text.contains("-") && !text.contains(",")){
            result.append(parseDash(text));
        } else if (text.contains(",")){
            result.append(parseComma(text));
        } else {
            result.append(parseInteger(text));
        }
        return result.toString().trim();
    }

    private String parseInteger(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();

        if (!cronPart.contains("-") && !cronPart.contains("*") && !cronPart.contains("/") && !cronPart.contains(",")){
            stringBuilder.append(cronPart).append(" ");
        }
        return stringBuilder.toString();
    }

    private String parseDash(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        int dashIndex = cronPart.indexOf("-");
        int before =Integer.parseInt(cronPart.substring(0, dashIndex));
        int after = Integer.parseInt(cronPart.substring(dashIndex+1));

        buildRange(before, after, stringBuilder);

        return stringBuilder.toString();
    }

    private String parseComma(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        String[] parts = cronPart.split(",");

        for (String part : parts) {
            if (part.contains("-")){
                stringBuilder.append(parseDash(part));
                continue;
            }
                stringBuilder.append(part).append(" ");
        }
        return stringBuilder.toString();
    }

    private String parseStars(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
            if (cronPart.length() == 1){
                buildRange(type.getMin(), type.getMax(), stringBuilder);
            } else {
                int indexStar = cronPart.indexOf("*");

                if (String.valueOf(cronPart.charAt(indexStar + 1)).equals("/")){
                    buildInterval(cronPart, indexStar, stringBuilder);}
            }
        return stringBuilder.toString();
    }

    private void buildRange(int typeMin, int typeMax, StringBuilder stringBuilder){
        for(int min = typeMin; min <= typeMax; min++){
            stringBuilder.append(min).append(" ");
        }
    }

    private void buildInterval(String cronPart, int indexStar, StringBuilder stringBuilder){
        String substring = cronPart.substring(indexStar+2);
        int interval = Integer.parseInt(substring);

        for (int i = type.getMin(); i <= type.getMax();  i += interval){
            stringBuilder.append(i).append(" ");
        }
    }
}