package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class QueryProcessor {

    public String process(String query) {
        try {
            query = query.toLowerCase();
            if (query.contains("shakespeare")) {
                return "William Shakespeare (26 April 1564 - 23 April 1616) was an " + "English poet, playwright, " +
                        "and" + " actor, widely regarded as the greatest " + "writer in the English language and the " +
                        "world's " + "pre-eminent dramatist.";
            }
            if (query.toLowerCase().contains("tum")) {
                return "The Technical University of Munich (TUM or TU Munich) is  a public research university in" +
                        " Munich," + " with additional" + " campuses in Garching, Freising, Heilbronn, Straubing," +
                        " and Singapore. A " + "technical university that specializes in engineering, technology," +
                        " medicine, and the applied and " + "natural sciences, it is organized into 11 schools " +
                        "and departments, and supported by numerous " + "research centers.";
            }
            if (query.toLowerCase().contains("name")) {
                return "michael";
            }
            if (query.toLowerCase().contains("largest")) {
                String[] split = query.split(":");
                String[] numbers = split[split.length - 1].split(",");
                return Arrays.stream(numbers).map(String::trim).map(Integer::parseInt)
                        .max(Comparator.comparing(Integer::valueOf)).get().toString();
            }
            if (query.toLowerCase().contains("plus")) {
                String[] split = query.split(" ");
                int result = (Integer.parseInt(split[split.length - 1]) + Integer.parseInt(split[split.length - 3]));
                return Integer.toString(result);
            }
            if (query.toLowerCase().contains("multiplied")) {
                String[] split = query.split(" ");
                long result = (Long.parseLong(split[split.length - 1]) * Long.parseLong(split[split.length - 3]));
                return Long.toString(result);
            }
            if (query.toLowerCase().contains("square and a cube")) {
                String[] split = query.split(":");
                String[] numbers = split[split.length - 1].split(",");
                return Arrays.stream(numbers).map(String::trim).map(Integer::parseInt).filter(value -> {
                    double square = Math.sqrt(value);
                    if (square != (long) square) return false;
                    double coube = Math.pow(value, (1.0 / 3));
                    return coube == (long) coube;
                }).map(Object::toString).collect(Collectors.joining(","));
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}
