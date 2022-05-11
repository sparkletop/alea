+ SequenceableCollection {
	xsum { |sum|
		var out = List(this.size);
		var iterator = Pshuf(this).repeat.asStream;
		//var numSkips = 0;

		while (
			{ sum > out.sum{|i|i.value} },
			{
				var space = sum - out.sum{|i|i.value};
				var candidate = iterator.next;
				if (candidate <= space,
					{ out.add(candidate) },
					{
						// numSkips = numSkips + 1;
						/* if the amount of remaining space is in the
						   array's lower quartile, we finish by adding
						   that amount of space as the final element  */
						(space < this.q1).if({ out.add(space) })
					}
				);
			}
		);
		//"skipRatio: %".format(numSkips/out.size).postln;
		^out.asArray;
	}

	prQuartile { |position|
		var sorted = this.copy.sort;
		var len = this.size;
		^len.even.if(
			{ [sorted[len * position - 1], sorted[len * position]].mean },
			{ sorted[(len * position).floor] }
		);
	}

	q1 {
		^this.prQuartile(0.25);
	}

	q2 {
		^this.prQuartile(0.5);
	}

	q3 {
		^this.prQuartile(0.75);
	}
}