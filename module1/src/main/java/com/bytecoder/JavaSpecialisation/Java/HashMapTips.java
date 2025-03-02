package com.bytecoder.JavaSpecialisation.Java;

import java.util.HashMap;
import java.util.Map;

public class HashMapTips {

        private String name;
        private static  int count = 0;

        public HashMapTips() {
            this.name = "ganesh";
        }

        public HashMapTips(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            count++;
            System.out.println("counting " + count);
            return 43 + (int) (System.currentTimeMillis() / 1000);
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("Comparing {self.name} with {other.name}");
            HashMapTips myMap = (HashMapTips) obj;
            return this.name.equals(myMap.name);
        }

        public int getCount() {
            return count;
        }

        public static void main(String[] args) {
            // Checking equality
            System.out.println(new HashMapTips().equals(new HashMapTips())); // True (Java uses equals method)
            System.out.println(new HashMapTips() == new HashMapTips()); // False (Different object references)

            // Simulating HashMap behavior
            Map<HashMapTips, String> mapDict = new HashMap<>();
            mapDict.put(new HashMapTips("name"), "1");
            mapDict.put(new HashMapTips("ganesh"), "2");
            mapDict.put(new HashMapTips("name"), "3");
            mapDict.put(new HashMapTips("name"), "4");

            System.out.println(new HashMapTips("name").getCount()); // 0 (new instance, count is not shared)
            System.out.println(mapDict);
            System.out.println(mapDict.get(new HashMapTips("name"))); // May return null due to unique hash codes
        }


}
