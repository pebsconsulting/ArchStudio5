package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAWorld;

public class ShadowThing extends ShadowThingBase {

	public static ShadowThing createIn(IBNAWorld world) {
		ShadowThing thing = getIn(world);
		if (thing == null) {
			thing = world.getBNAModel().addThing(new ShadowThing());
		}
		return thing;
	}

	public static ShadowThing getIn(IBNAWorld world) {
		return (ShadowThing) world.getBNAModel().getThing(ShadowThing.class);
	}

	protected ShadowThing() {
		super(ShadowThing.class);
	}
}
