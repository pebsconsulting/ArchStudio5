package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=GradientFilled].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasGradientFilled extends org.archstudio.bna.IThing,
    org.archstudio.bna.facets.IHasColor, org.archstudio.bna.facets.IHasSecondaryColor {

  public static final IThingKey<java.lang.Boolean> GRADIENT_FILLED_KEY = ThingKey.create(
      com.google.common.collect.Lists.newArrayList("gradientFilled", IHasGradientFilled.class));

  public boolean isGradientFilled();

}
