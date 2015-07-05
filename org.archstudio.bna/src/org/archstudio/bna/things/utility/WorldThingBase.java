package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * org.archstudio.bna/Package[name=org.archstudio.bna.things.utility]/Thing[name=World].
 */

@SuppressWarnings("all")
@NonNullByDefault
public abstract class WorldThingBase extends org.archstudio.bna.things.AbstractThing
    implements org.archstudio.bna.IThing, org.archstudio.bna.facets.IHasMutableBoundingBox,
    org.archstudio.bna.facets.IHasMutableWorld {

  public WorldThingBase(@Nullable Object id) {
    super(id);
  }

  @Override
  public IThingPeer<? extends WorldThing> createPeer(IBNAView view, ICoordinateMapper cm) {
    return new WorldThingPeer<>((WorldThing) this, view, cm);
  }

  @Override
  protected void initProperties() {
    initProperty(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY,
        new org.eclipse.swt.graphics.Rectangle(0, 0, 30, 20));
    addShapeModifyingKey(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY);
    initProperty(org.archstudio.bna.facets.IHasWorld.WORLD_KEY, null);
    super.initProperties();
  }

  @Override
  public org.eclipse.swt.graphics.Rectangle getBoundingBox() {
    return get(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY);
  }

  /* package */ org.eclipse.swt.graphics.Rectangle getRawBoundingBox() {
    return getRaw(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY);
  }

  @Override
  public void setBoundingBox(org.eclipse.swt.graphics.Rectangle boundingBox) {
    set(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY, boundingBox);
  }

  /* package */ org.eclipse.swt.graphics.Rectangle setRawBoundingBox(
      org.eclipse.swt.graphics.Rectangle boundingBox) {
    return setRaw(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY, boundingBox);
  }

  @Override
  public @Nullable org.archstudio.bna.IBNAWorld getWorld() {
    return get(org.archstudio.bna.facets.IHasWorld.WORLD_KEY);
  }

  /* package */ @Nullable
  org.archstudio.bna.IBNAWorld getRawWorld() {
    return getRaw(org.archstudio.bna.facets.IHasWorld.WORLD_KEY);
  }

  @Override
  public void setWorld(@Nullable org.archstudio.bna.IBNAWorld world) {
    set(org.archstudio.bna.facets.IHasWorld.WORLD_KEY, world);
  }

  /* package */ @Nullable
  org.archstudio.bna.IBNAWorld setRawWorld(@Nullable org.archstudio.bna.IBNAWorld world) {
    return setRaw(org.archstudio.bna.facets.IHasWorld.WORLD_KEY, world);
  }

}
