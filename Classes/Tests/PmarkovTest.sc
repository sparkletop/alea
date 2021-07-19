PmarkovTest1 : UnitTest {
	test_check_classname {
		var result = Pmarkov.new;
		this.assert(result.class == Pmarkov);
	}
}


PmarkovTester {
	*new {
		^super.new.init();
	}

	init {
		PmarkovTest1.run;
	}
}
