# Cron Parser Challenge
This Java-based project parses Cron Expressions in the standard cron format with five time fields 
(minute, hour, day of month, month, and day of week) plus a command field.

An example cron expression is :
`*/15 0 1,15 * 1-5 /usr/bin/find`

## Pre-requisites
- Java 21 or higher (required)
- IntelliJ IDEA (recommended for running source code)
## How to run the executable:
The executable is located at https://github.com/AlexanderVerheecke/DeliverooTakeHome/blob/main/cronParser.jar

Under the hamburger icon at the top right side of the repo, click **Download**. This will download the file to wherever the browser default to saving files.

Using your terminal, `cd` into the directory containing the downloaded `.jar` file. E.g. if it was saved to the `Downloads` folder, `cd ~/Downloads` into it.

The executable can be ran using the following command:

`java -jar cronParser.jar your-cron-expression"`

e.g `java -jar cronParser.jar "*/15 0 1,5-10 * * /usr/bin/find"`

This will then output the result in a table-like structure. The above would output:
```
minute        0 15 30 45
hour          0
day of month  1 5 6 7 8 9 10
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   0 1 2 3 4 5 6
command       /usr/bin/find
```


## How to run the source code
1. **Clone the repo**    
   Clone using the following command `git clone https://github.com/AlexanderVerheecke/DeliverooTakeHome.git`


2. **Open in IntelliJ (or another IDE)** 
   Open the folder in IntelliJ, it will automatically import the Gradle project.
3. **Run source code**
   The source code can be ran in the `Main` class. Though to run the code with a hardcoded command, you will need to comment out the argument length check and replace `new Cron(args[0]` with the desired command like so:
```
//if (args.length == 0) {
//           System.out.println("ERROR, no cron expression provided. Example cron expression to be given as parameter */15 0 1,15 * 1-5 /usr/bin/find:");
//           return;
// }
 ...
 Cron cron =  new Cron("*/15 0 1,15 * 1-5 /usr/bin/find");
 ...
 ```

The tests can be ran by running the `CronTest` class.
   





