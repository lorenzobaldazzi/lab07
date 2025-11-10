package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

   
    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        private Month(int days){
            this.days = days;
        }

        public int getDays(){
            return this.days;
        }

        public static Month fromString(final String nameMonth ){
            Objects.requireNonNull(nameMonth);
            
            final String formatName = nameMonth.trim().toUpperCase(Locale.ROOT);
            int countFindMonth = 0;
            Month find = null;
            for(final Month tmp: Month.values()){
                if(tmp.name().startsWith(formatName)){
                    find = tmp;
                    countFindMonth++;                  
                }
            }

            if(countFindMonth == 0){
                throw new IllegalArgumentException("mese non trovato");
            }else if(countFindMonth > 1){
                throw new IllegalArgumentException("è stato trovato piu di un mese con questo nome");
            }

            return find;

        }

    }
    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private static class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(final String arg0,final String arg1) {

            final Month m1 = Month.fromString(arg0);
            final Month m2 = Month.fromString(arg1);
            return Integer.compare(m1.ordinal(), m2.ordinal());

        }

      
    }

    private static class SortByDate implements Comparator<String>{
        @Override
        public int compare(final String arg0,final String arg1) {

            final Month m1 = Month.fromString(arg0);
            final Month m2 = Month.fromString(arg1);
            return Integer.compare(m1.getDays(), m2.getDays());

        }

       
    }
}
