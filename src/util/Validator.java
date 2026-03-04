package util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Validator {

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age >= 15 && age <= 110;
    }

    public static boolean isValidScore(double score) {
        return score >= 0.0 && score <= 10.0;
    }

    public static boolean isValidNganh(String nganh) {
        return nganh.equals("CNTT") || nganh.equals("KTPM");
    }

    public static boolean isValidMaSV(String masv, String nganh) {
        if (!masv.matches("\\d{10}")) return false;

        if (nganh.equals("CNTT") && masv.startsWith("455105"))
            return true;

        if (nganh.equals("KTPM") && masv.startsWith("455109"))
            return true;

        return false;
    }

    public static String chuanHoaTen(String name) {
        name = name.trim().toLowerCase();
        String[] arr = name.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(Character.toUpperCase(s.charAt(0)))
              .append(s.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }
}