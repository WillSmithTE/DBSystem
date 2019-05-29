package ses1grp6.DBSystemBE.controllers;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.Donor;
import ses1grp6.DBSystemBE.repositories.CharityRepository;
import ses1grp6.DBSystemBE.repositories.DonorRepository;

import java.nio.charset.Charset;
import java.util.Random;


public class SeedController {

    private CharityRepository charityRepository;
    private DonorRepository donorRepository;
    // private ApplicationRepository applicationRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedCharityTable();
        seedDonorTable();
        // seedApplicationTable();
    }
    

    private void seedCharityTable(){
        // String sql = "SELECT c.name, c.email FROM charity c LIMIT 1";
        // List<Charity> data = JdbcTemplate().query(sql, (resultSet, rowNum) -> null);
        // if(data == null || data.size() <= 0) {
        for(int i = 0; i < 100; i++){

            Charity charity = new Charity();
            charity.setName("Charity " + getRandomString(5));
            charity.setEmail(getRandomString(5) + "@charitytest.com");
            charity.setPassword("test123");
            charity.setContactNumber("0402000000");
            charityRepository.save(charity);

        }

        // }
    }

    private void seedDonorTable(){
        // String sql = "SELECT d.name, d.email FROM donor d LIMIT 1";
        // List<Charity> data = JdbcTemplate().query(sql, (resultSet, rowNum) -> null);
        // if(data == null || data.size() <= 0) {
        for(int i = 0; i < 100; i++){

            Donor donor = new Donor();
            donor.setName("Donor " + getRandomString(5));
            donor.setEmail(getRandomString(5) + "@donortest.com");
            donor.setPassword("test123");
            donor.setContactNumber("0402000000");
            donorRepository.save(donor);

        }
        // }
    }


    // private void seedApplicationTable(){
  
    //     for(int i = 0; i < 100; i++){

    //         Application application = new Application();
    //         application.setName("Donor " + getRandomString(5));
    //         application.setEmail(getRandomString(5) + "@donortest.com");
    //         application.setPassword("test123");
    //         application.setContactNumber(getRandomNumber());
    //         applicationRepository.save(application);
    //     }
    // }

    static String getRandomString(int n) { 
  
        // length is bounded by 256 Character 
        byte[] array = new byte[256]; 
        new Random().nextBytes(array); 
  
        String randomString  = new String(array, Charset.forName("UTF-8")); 
  
        // Create a StringBuffer to store the result 
        StringBuffer r = new StringBuffer(); 
  
        // Append first 20 alphanumeric characters 
        // from the generated random String into the result 
        for (int k = 0; k < randomString.length(); k++) { 
  
            char ch = randomString.charAt(k); 
  
            if (((ch >= 'a' && ch <= 'z')
                 || (ch >= 'A' && ch <= 'Z') 
                 || (ch >= '0' && ch <= '9')) 
                && (n > 0)) { 
  
                r.append(ch); 
                n--; 
            } 
        } 
  
        // return the resultant string 
        return r.toString(); 
    } 

    // private int getRandomNumber(){
    //     int n = 100000 + random_float() * 900000;
    //     n = Integer.toString(n);
    //     return n;
    // }
}

