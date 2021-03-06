package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractControlThingPeer<T extends AbstractControlThing, C extends Control> extends
		AbstractThingPeer<T> {

	protected C control = null;

	public AbstractControlThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected abstract C createControl(IBNAView view, ICoordinateMapper cm);

	protected void remove(IBNAView view) {
		try (Finally lock = BNAUtils.lock()) {
			model.removeThing(t);
		}
	}

	protected Rectangle getBounds(IBNAView view, ICoordinateMapper cm) {
		return cm.worldToLocal(t.getBoundingBox());
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		SWTWidgetUtils.async(view.getBNAUI().getComposite(), new Runnable() {
			@Override
			public void run() {
				try (Finally lock = BNAUtils.lock()) {
					if (control == null) {
						control = createControl(view, cm);
						if (control == null) {
							return;
						}
					}

					Rectangle newBounds = getBounds(view, cm);
					Rectangle oldBounds = control.getBounds();
					if (!oldBounds.equals(newBounds)) {
						if (oldBounds.width != newBounds.width || oldBounds.height != newBounds.height) {
							control.setSize(newBounds.width, newBounds.height);
							if (control instanceof Composite) {
								((Composite) control).layout(true, true);
								control.pack(true);
							}
						}
						control.setLocation(newBounds.x, newBounds.y);
					}
				}
			}
		});

		return true;
	}

	@Override
	public void dispose() {
		control = SWTWidgetUtils.quietlyDispose(control);
		super.dispose();
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
