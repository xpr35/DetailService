import com.detailservice.model.Profiles;
import com.detailservice.service.DetailService;
import org.junit.Test;

import javax.persistence.PersistenceException;

public class DetailServiceTest {
    @Test
    public void SuccessGetProfiles() throws PersistenceException, InterruptedException {
        DetailService detailService = new DetailService();
        Profiles profiles = detailService.getDetails(11111L);
        assert(profiles != null);
    }
}
