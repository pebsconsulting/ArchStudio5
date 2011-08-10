package org.archstudio.bna.things;

import java.util.Set;

import org.archstudio.bna.facets.IHasShape;
import org.archstudio.bna.facets.IRelativeMovable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.collect.Sets;

public abstract class AbstractRelativeMovableThing extends AbstractThing implements IRelativeMovable, IHasShape {

	public AbstractRelativeMovableThing(Object id) {
		super(id);
	}

	@Override
	public abstract void moveRelative(Point worldDelta);

	@Override
	public void moveRelative(Dimension worldDelta) {
		moveRelative(new Point(worldDelta.width, worldDelta.height));
	}

	@Override
	public void addShapeModifyingKey(final IThingKey<?> key) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Set<Object> keys = Sets.newHashSet(get(SHAPE_MODIFYING_KEYS_KEY));
				keys.add(key);
				set(SHAPE_MODIFYING_KEYS_KEY, keys);
			}
		});
	}

	@Override
	public boolean isShapeModifyingKey(IThingKey<?> key) {
		return get(SHAPE_MODIFYING_KEYS_KEY).contains(key);
	}
}