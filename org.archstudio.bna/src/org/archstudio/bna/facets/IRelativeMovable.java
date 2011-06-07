package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public interface IRelativeMovable extends IThing, IHasReferencePoint {

	public static final String USER_MAY_MOVE = "userMayMove";

	public void moveRelative(Point worldDelta);

	public void moveRelative(Dimension worldDelta);

	public void setReferencePoint(Point worldPoint);

}
