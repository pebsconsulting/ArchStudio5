package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=Size].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasSize extends org.archstudio.bna.IThing {

  public static final IThingKey<java.awt.Dimension> SIZE_KEY =
      ThingKey.create(com.google.common.collect.Lists.newArrayList("size", IHasSize.class));

  public java.awt.Dimension getSize();

}
