package com.bytecoder.java17;


//reverse string and number list/collection, array	string to array


// default object methods -> hashcode, toString, equals, toCompare,


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class JavaTips {


    

    public static void hashmapSorting(){
        Map<String, Integer> map = new HashMap<>();
        map.put("John", 32);
        map.put("Alice", 25);
        map.put("Alicee", 25);
        map.put("Bob", 35);

        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed().thenComparing((e1,e2)-> e2.getKey().length()-e1.getKey().length())).forEach(System.out::println);

        List<Map.Entry<String, Integer>> set = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        set.stream().filter((e)-> e.getValue()>30).forEach(System.out::println);



        // Filter by value (e.g., values greater than 30) and sort by key
        Map<String, Integer> sortedAndFilteredMap = map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 30)
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e1,
                        LinkedHashMap::new));

        // Print the sorted and filtered map
        sortedAndFilteredMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /***
     * StringBuilder or StringBuffer: Easiest and most efficient way to reverse a string.
     * Loop: Manually reverse by iterating over the string.
     * Character Array: Reverse the string by converting it to an array.
     * Recursion: Reverse using a recursive method.
     * @param original string
     */
    public static void reverseString(String original) {
        StringBuilder sb = new StringBuilder(original);
        String reversed = sb.reverse().toString();
        System.out.println(reversed);  // Output: "olleH"
    }


    public static void printNumbers(int... numbers) {
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void handleDateTime(){
        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);


        // Create a LocalDateTime instance
        LocalDateTime localDateTime = LocalDateTime.now();  // e.g., 2024-10-04T14:30:00

        // Assign a time zone (Asia/Kolkata)
        ZoneId zone = ZoneId.of("UTC");

        // Convert LocalDateTime to ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(zone);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH/mm/ss");

        // Print the ZonedDateTime
        System.out.println("ZonedDateTime with IST: " + zonedDateTime.format(dateTimeFormatter));

//        ZonedDateTime zonedDateTime = ZonedDateTime.now();
//        System.out.println(zonedDateTime);

        // Get the current time in UTC using Instant
        Instant nowUtc = Instant.now();

        // Convert the Instant to a ZonedDateTime in UTC
        ZonedDateTime zonedDateTimeUtc = ZonedDateTime.ofInstant(nowUtc, ZoneId.of("Asia/Kolkata"));
        System.out.println("ZonedDateTime in UTC: " + zonedDateTimeUtc.format(dateTimeFormatter) + " zone " + zonedDateTimeUtc.getZone());

        // Convert the UTC ZonedDateTime to IST time zone
        ZonedDateTime zonedDateTimeIst = zonedDateTimeUtc.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println("ZonedDateTime in IST: " + zonedDateTimeIst);




    }

    public static int sortArray (int[][] events){
//        Arrays.sort(pair, (p1, p2)->( p1[1] - p1[0]) - (p2[1]-p2[0]));
//        return pair;

          Arrays.sort(events, (p1, p2)-> p1[1]-p2[1]);
        HashMap<Integer, Integer> days = new HashMap<>();

            for (int i = 0; i <events.length; i++) {
            int temp= events[i][1];
            while(days.get(temp) != null && temp >= events[i][0] && temp > 1){
                temp--;
                if(temp==0){
                    break;
                }
            }
            if(days.get(temp) == null){
                System.out.println("temp " + temp) ;
                System.out.println(events[i][1]);
                days.put(temp, events[i][1]);
            }
        }
        return days.size();
    }


    public static void main(String[] args) {

        int [][] pairs = {{-2147483646,-2147483645},{2147483646,2147483647}};
//        sortArray(pairs);

        Arrays.sort(pairs, (p1, p2)->Math.abs(p1[0]) - Math.abs(p2[0]));
        for (int i = 0; i <pairs.length; i++) {
            System.out.println(pairs[i][0] + " " +  pairs[i][1]);
        }



//        reverseString("ganesh");
//        hashmapSorting();
//
//        printNumbers(1, 2, 3, 4, 5);
//        printNumbers(10, 20);
//        printNumbers(new int[]{11, 22});
//        printNumbers();  // No arguments can also be passed
//
////        handleDateTime();
////        handleDateTime2();
//
//
//           // Step 1: Define the string with timezone information
//        String timestampWithZone = "2024-10-04T14:30:00+00:30";
//
//        // Step 2: Parse the string into an OffsetDateTime (which includes timezone information)
//        OffsetDateTime offsetDateTime = OffsetDateTime.parse(timestampWithZone);
//
//        // Step 3: Extract the LocalDateTime from the OffsetDateTime, discarding the timezone
//        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
//
//        // Step 4: Print the LocalDateTime
//        System.out.println("LocalDateTime (without time zone): " + localDateTime);

        System.out.println(solve("abc", "ahbgdc", 0,0));


    }



    public static boolean solve(String s,String t, int i, int j){
        if( j > t.length() ){
            return false;
        }
        if(s.length()==i){
            return true;
        }
        boolean ans;
        if(t.charAt(j)==s.charAt(i)){
            return solve(s,t, i+1, j+1);
        }else{
            return solve(s,t,i,j+1);
        }
    }
// Java Program to Demonstrate conversion of List to Array
// Using stream


    // Main class
    class GFG {

        // Main driver method
        public static void main(String[] args)
        {
//            PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparing(Collectors.toList()));
            // Creating an empty LinkedList of string type
            List<String> list = new LinkedList<String>();

            // Adding elements to above LinkedList
            // using add() method
            list.add("Geeks");
            list.add("for");
            list.add("Geeks");
            list.add("Practice");

            // Storing size of List
            int n = list.size();

            // Converting List to array via scope resolution
            // operator using streams
            String[] arr = (String[]) list.toArray();

            // Printing elements of array
            // using enhanced for loop
            for (String x : arr)
                System.out.print(x + " ");
        }
    }

    private static void handleDateTime2() {
           // LocalDateTime.now() does not take the zone from the system's default or any other zone context. Instead, LocalDateTime is specifically designed to represent date and time without any time zone information. When you call LocalDateTime.now(), it retrieves the current date and time from the system clock
        // Step 1: Create a LocalDateTime (without any time zone)
        LocalDateTime localDateTime = LocalDateTime.now(); // e.g., 2024-10-04T14:30:00
        System.out.println("LocalDateTime: " + localDateTime);

        // Step 2: Assign the local date-time to a specific time zone (e.g., Asia/Kolkata)
        ZoneId sourceZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedDateTime = localDateTime.atZone(sourceZone);
        System.out.println("ZonedDateTime in source time zone (Asia/Kolkata): " + zonedDateTime);

        // Step 3: Convert to another time zone (e.g., America/New_York)
        ZoneId targetZone = ZoneId.of("America/New_York");
        ZonedDateTime targetZonedDateTime = zonedDateTime.withZoneSameInstant(targetZone);
        System.out.println("ZonedDateTime in target time zone (America/New_York): " + targetZonedDateTime);
    }
}

// todo date, datetime and timestamp  handling in db, python and java


// Java does not have a direct mechanism for setting default arguments in functions, as languages like Python do.
// Java does not have a direct mechanism for Returning multiple values in functions, as languages like Python do. a,b= function(args)
// Java does not have built-in support for *args and **kwargs like Python does.
// In Python, you can easily swap two variables in a single line using the syntax a, b = b, a. However, in Java, there's no direct syntax for swapping values like this.



// java utils classes