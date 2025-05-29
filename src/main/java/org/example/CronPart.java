package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CronPart {

    private String text;
    private CronPartType type;

    public CronPart(String text, CronPartType type) throws IllegalArgumentException{
        this.text = text;
        this.type = type;

        parseStars(text, type);
    }

    private void parseStars(String cronPart, CronPartType type){
        StringBuilder stringBuilder = new StringBuilder();
        if (cronPart.startsWith("*")){
            if (cronPart.length() ==1){
                buildRange(type, stringBuilder);
                System.out.println(stringBuilder);

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

    private void buildRange(CronPartType type, StringBuilder stringBuilder){
        for(int min = type.getMin(); min < type.getMax(); min++){
            stringBuilder.append(String.valueOf(min)).append(" ");
        }
    }
}