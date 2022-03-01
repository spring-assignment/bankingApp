package com.cts.springboot.banking.entities;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//import java.util.Date;
//
//public class AccountDate {
//
//
//        /*String pattern = "dd/MM/yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//        String date1 = simpleDateFormat.format(new Date());
//        System.out.println(date1);*/
//
//        //System.out.println(formattedDate);
//
//       /* System.out.println("SHORT format: " + formattedDate);
//        LocalDate birthDate = LocalDate.of(1999, 05, 01);
//        Period period = Period.between(birthDate, LocalDate.parse(date1));
//        System.out.println(period.getYears());*/
//
//
//
//        LocalDate date = LocalDate.now();
//        //String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        LocalDate birthDate = LocalDate.of(1999, 05, 01);
//        Period period = Period.between(birthDate, date);
//
//
//    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
//        if ((birthDate != null) && (currentDate != null)) {
//            return Period.between(birthDate, currentDate).getYears();
//        } else {
//            return 0;
//        }
//    }
//
//
//
//
//
//}
