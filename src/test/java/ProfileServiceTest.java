import com.detailservice.service.ProfileService;
import org.junit.Test;

public class ProfileServiceTest {

    @Test
    public void SuccessRequestProfileTest() {
        ProfileService profileService = new ProfileService("https://randomuser.me/api/?phone=1111111111&inc=name,email", 5);
        profileService.getProfile();
    }

    @Test(expected=Exception.class)
    public void FailRequestProfileTest() throws Exception {
        ProfileService profileService = new ProfileService("https://randomuser.me/api/?phone=1111111111&inc=name, email", 5);
        profileService.requestProfile();
    }
}
