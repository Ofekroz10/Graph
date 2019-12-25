package utils;

import java.util.Collection;

import dataStructure.node_data;

public class DistanceForm extends Select2VerForm {

	public DistanceForm(Collection<node_data> ver, GraphicWin base) {
		super(ver, base);
	}
	@Override
	public void applyAlgo()
	{
		base.applyDisAlgo(v1, v2);
	}

}
