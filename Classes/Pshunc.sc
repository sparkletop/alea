Pshunc : ListPattern {
	var <>length;
	*new { arg list, length=4, repeats=1;
		^super.new(list, repeats)
		.length_(length)
	}

	embedInStream { arg inval;
		var item, stream;

		// Two scrambles are needed in case the list is not long enough
		var localList = list.copy.scramble
		.wrapExtend(length ? list.size).scramble;

		repeats.value(inval).do({
			localList.size.do({ arg i;
				item = localList.wrapAt(i);
				inval = item.embedInStream(inval);
			});
		});
		^inval;
	}
}