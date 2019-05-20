package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.*;
import ses1grp6.DBSystemBE.repositories.*;
import java.util.*; 
import java.nio.charset.*; 

@RequestMapping("/seed")
public class AuthController {

    @RequestMapping(value = "all", method = RequestMethod.GET)
        @EventListener
        private void seed(ContextRefreshedEvent event){
            seedCharityTable();
            seedDonorTable();
            seedApplicationTable();
        }
    }


    private void seedCharityTable(){
        String sql = "SELECT c.name, c.email FROM charity c IMIT 1";
        
        List<Charity> data = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(data == null || data.size() <= 0) {
             for(int i = 0; i < 100; i++){

                Charity charity = new Charity();
                charity.setName("Charity " + getRandomString(5));
                charity.setEmail(getRandomString(5) + "@charitytest.com");
                charity.setPassword(new BCryptPasswordEncoder().encode("test123"));
                charity.setContactNumber(getRandomNumber());
                charityRepository.save(charity);

             }

        }
    }

    private void seedDonorTable(){
        String sql = "SELECT d.name, d.email FROM donor d IMIT 1";
        
        List<Charity> data = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(data == null || data.size() <= 0) {
             for(int i = 0; i < 100; i++){

                Donor donor = new Donor();
                donor.setName("Donor " + getRandomString(5));
                donor.setEmail(getRandomString(5) + "@donortest.com");
                donor.setPassword(new BCryptPasswordEncoder().encode("test123"));
                donor.setContactNumber(getRandomNumber());
                donorRepository.save(donor);

             }
        }
    }


    // private void seedApplicationTable(){
  
    //     for(int i = 0; i < 100; i++){

    //         Application application = new Application();
    //         application.setName("Donor " + getRandomString(5));
    //         application.setEmail(getRandomString(5) + "@donortest.com");
    //         application.setPassword(new BCryptPasswordEncoder().encode("test123"));
    //         application.setContactNumber(getRandomNumber());
    //         applicationRepository.save(donor);
    //     }
    // }

    static String getRandomString(int n) 
    { 
  
        // length is bounded by 256 Character 
        byte[] array = new byte[256]; 
        new Random().nextBytes(array); 
  
        String randomString 
            = new String(array, Charset.forName("UTF-8")); 
  
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

    private int getRandomNumber(){
        int n = 100000 + random_float() * 900000;
        return n.toString();
    }
}

