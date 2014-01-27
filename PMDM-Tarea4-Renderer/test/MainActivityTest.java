import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import com.foc.activities.MainActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	
	private MainActivity mainActivity;

	@Before
	public void setUp(){
		mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
	}

	@Test
	public void activityNotNull(){
		assertNotNull(mainActivity);
	}
	
	@Test
	public void test(){
				
	}

}

