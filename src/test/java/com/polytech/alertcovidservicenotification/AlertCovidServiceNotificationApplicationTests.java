package com.polytech.alertcovidservicenotification;

import com.polytech.alertcovidservicenotification.models.ContactLocation;
import com.polytech.alertcovidservicenotification.models.ContactLocationRepository;
import com.polytech.alertcovidservicenotification.models.User;
import com.polytech.alertcovidservicenotification.models.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class AlertCovidServiceNotificationApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Test
	void testUserBD() {
		List<User> user = userRepository.findAll();
		if(!user.isEmpty()){
			System.out.println(user.get(0).getFirstname());
		}
		else{
			fail("Bd vide");
		}
	}
	@Autowired
	private ContactLocationRepository contactLocationRepository;
	@Test
	void testContactLocationBD(){
		List<ContactLocation> contactLocations = contactLocationRepository.findAll();
		if(!contactLocations.isEmpty()){
			contactLocations.forEach(contact ->
					System.out.println(contact.getDate()
					));
		}
		else{
			fail("Bd vide");
		}
	}

}
