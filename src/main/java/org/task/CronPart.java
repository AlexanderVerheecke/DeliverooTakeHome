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

    public String parseText(CronPartType type, String text) throws IllegalArgumentException{
        StringBuilder result = new StringBuilder();

        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("CronPart text cannot be null or empty");
        }

        text = text.trim();
        try {
            if (text.startsWith("*")){
                result.append(parseStars(text, type));
            } else if (text.contains("-") && !text.contains(",")){
                result.append(parseDash(text));
            } else if (text.contains(",")){
                result.append(parseComma(text));
            } else {
                result.append(parseInteger(text));
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid number format in cron expression: " + text, e );
        }

        return result.toString().trim();
    }

    private String parseInteger(String cronPart) throws IllegalArgumentException{
        StringBuilder stringBuilder = new StringBuilder();

        if (!cronPart.contains("-") && !cronPart.contains("*") && !cronPart.contains("/") && !cronPart.contains(",")){
            int value = Integer.parseInt(cronPart);
            validateNumbers(value, type);
            stringBuilder.append(cronPart).append(" ");
        }
        return stringBuilder.toString();
    }

    private String parseDash(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        int dashIndex = cronPart.indexOf("-");

        if (dashIndex == 0 || dashIndex == cronPart.length() - 1){
            throw new IllegalArgumentException("'-' cannot be at start or end, likely missing value of interval");
        }
        int before =Integer.parseInt(cronPart.substring(0, dashIndex));
        int after = Integer.parseInt(cronPart.substring(dashIndex+1));

        validateNumbers(before, type);
        validateNumbers(after, type);

        if (before > after){
            throw new IllegalArgumentException(String.format("Start value %d is greater than end value %d", before, after));
        }

        buildRange(before, after, stringBuilder);

        return stringBuilder.toString();
    }

    private String parseComma(String cronPart){
        StringBuilder stringBuilder = new StringBuilder();
        String[] parts = cronPart.split(",");

        if (parts.length < 2){
            throw new IllegalArgumentException("Comma separation list must have at least two values");
        }

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()){
                throw new IllegalArgumentException("Comma separation cannot have empty values");
            }
            if (part.contains("-")){
                stringBuilder.append(parseDash(part));

            } else {
                stringBuilder.append(part).append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private String parseStars(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();

            if (cronPart.length() == 1){
                buildRange(type.getMin(), type.getMax(), stringBuilder);
            } else {
                int indexStar = cronPart.indexOf("*");

                if (indexStar + 1 >= cronPart.length() || !String.valueOf(cronPart.charAt(indexStar + 1)).equals("/")) {
                    throw new IllegalArgumentException("* must be followed by / for intervals");
                }

                if (indexStar + 2 >= cronPart.length()){
                    throw new IllegalArgumentException("Interval value missing after */");
                }

                buildInterval(cronPart, indexStar, stringBuilder, type);
            }
        return stringBuilder.toString();
    }

    private void buildRange(int typeMin, int typeMax, StringBuilder stringBuilder){
        for(int min = typeMin; min <= typeMax; min++){
            stringBuilder.append(min).append(" ");
        }
    }

    private void buildInterval(String cronPart, int indexStar, StringBuilder stringBuilder, CronPartType type){
        String substring = cronPart.substring(indexStar+2);
        int interval = Integer.parseInt(substring);

        if (interval <= 0 || interval > type.getMax() - type.getMin() + 1) {
            throw new IllegalArgumentException(String.format(
                    "Interval %d of type %s out of range, it must be within range %d-%d", interval, type.name(), type.getMin(), type.getMax()));
        }
        for (int i = type.getMin(); i <= type.getMax();  i += interval){
            stringBuilder.append(i).append(" ");
        }
    }

    private void validateNumbers(int value, CronPartType type){
        if (value < type.getMin() || value > type.getMax()){
            throw new IllegalArgumentException(String.format("Value %d is out of range for type %s, possible values are [%d-%d]", value, type.name(), type.getMin(), type.getMax()));
        }
    }
}