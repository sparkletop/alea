Pmarkov : ListPattern {
	var <>weights, <>startPos;
	*new { arg states, weights, repeats=inf, startPos=0;
		var error = Error('List size mismatch. See Pmarkov help file.');

		if (states.size != weights.size, { error.throw });

		^super.new(states, repeats)
		.weights_(
			weights.collect{ |w|
				if (states.size != w.size, { error.throw });
				w.normalizeSum;
			}
		)
		.startPos_(startPos)
	}
	embedInStream {  arg inval;
		var item, wVal,
		nextIndex = startPos.value(inval),
		wStr = weights.asStream;
		repeats.value(inval).do({ arg i;
			wVal = wStr.next(inval);
			if(wVal.isNil) { ^inval };
			item = list.at(nextIndex);
			inval = item.embedInStream(inval);
			nextIndex = wVal.at(nextIndex).windex;
		});
		^inval
	}
	storeArgs { ^[ list, weights, repeats ] }
}

