Pxsum : ListPattern {
	var <>sum;
	*new { arg list, sum=1, repeats=1;
		^super.new(list, repeats)
		.sum_(sum);
	}

	embedInStream { arg inval;
		var item;
		var localList = list.copy.xsum(sum);

		repeats.value(inval).do({
			localList.size.do({ |i|
				item = localList.wrapAt(i);
				inval = item.embedInStream(inval);
			});
		});
		^inval;
	}
}