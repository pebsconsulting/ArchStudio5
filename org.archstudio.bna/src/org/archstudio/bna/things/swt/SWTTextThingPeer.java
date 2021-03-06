package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class SWTTextThingPeer<T extends SWTTextThing> extends AbstractControlThingPeer<T, Text> {

	public SWTTextThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	protected Text createControl(final IBNAView view, ICoordinateMapper cm) {
		// we only want one control for a thing, not many for each peer when there are recursive worlds
		for (Control control : view.getBNAUI().getComposite().getChildren()) {
			if (t.equals(control.getData())) {
				return null;
			}
		}

		final Text control = new Text(view.getBNAUI().getComposite(), SWT.BORDER | SWT.FLAT | SWT.SINGLE);
		// necessary for the AWT UI
		control.moveAbove(null);
		control.setData(t);
		control.setFocus();
		control.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				remove(view);
			}
		});
		control.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				try (Finally lock = BNAUtils.lock()) {
					if (e.character == SWT.CR) {
						t.setText(control.getText());
						remove(view);
					}
					else if (e.character == SWT.ESC) {
						remove(view);
					}
				}
			}
		});
		control.setText(t.getText());
		control.selectAll();
		control.forceFocus();
		return control;
	}

	@Override
	protected Rectangle getBounds(IBNAView view, ICoordinateMapper cm) {
		Rectangle bounds = super.getBounds(view, cm);

		if (control != null) {
			GC gc = null;
			try {
				gc = new GC(control.getDisplay());
				if (control.getFont() != null) {
					gc.setFont(control.getFont());
				}
				int minHeight = gc.getFontMetrics().getHeight();
				int minWidth = gc.textExtent(control.getText()).x + gc.getFontMetrics().getAverageCharWidth() * 4;
				Rectangle newBounds = control.computeTrim(bounds.x, bounds.y, minWidth, minHeight);
				bounds.width = Math.max(newBounds.width, bounds.width);
				bounds.height = Math.max(newBounds.height, bounds.height);
			}
			finally {
				SWTWidgetUtils.quietlyDispose(gc);
			}
		}

		return bounds;
	}
}