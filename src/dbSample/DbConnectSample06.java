package dbSample;

import java.util.Iterator;
import java.util.List;

public class DbConnectSample06 {

    public static void main(String[] args) {
        CountryDAO countryDAO = new CountryDAO();

        List<Country> countries = countryDAO.getCountryFromName("Aruba");

        Iterator<Country> it = countries.iterator();
        while(it.hasNext()) {
            Country country = it.next();
            System.out.println(country.getName());
            System.out.println(country.getPopulation());
        }
    }
}
