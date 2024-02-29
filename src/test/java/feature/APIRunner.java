package feature;

import com.intuit.karate.junit5.Karate;

public class APIRunner {

	Karate testusers() {

		return Karate.run("karate").relativeTo(getClass());
	}
}
