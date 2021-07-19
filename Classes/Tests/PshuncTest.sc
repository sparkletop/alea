PshuncTest1 : UnitTest {
	test_check_classname {
		var result = Pshunc.new;
		this.assert(result.class == Pshunc);
	}
}


PshuncTester {
	*new {
		^super.new.init();
	}

	init {
		PshuncTest1.run;
	}
}
