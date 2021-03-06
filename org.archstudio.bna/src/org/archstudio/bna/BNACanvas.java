package org.archstudio.bna;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.ui.utils.AutodetectBNAUI;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.LinearCoordinateMapper;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

public class BNACanvas extends Composite implements ControlListener, SelectionListener, MouseListener,
		IBNAModelListener, ICoordinateMapperListener {

	protected final int bnaUIStyle;
	protected final IBNAView view;
	protected IBNAUI bnaUI;
	protected ScrollBar hBar;
	protected ScrollBar vBar;
	protected boolean disposeView = false;

	public BNACanvas(Composite parent, int style, IBNAWorld world) {
		this(parent, style, new DefaultBNAView(null, world, new LinearCoordinateMapper()));
		disposeView = true;
	}

	public BNACanvas(Composite parent, int style, final IBNAView view) {
		super(parent, style & (SWT.H_SCROLL | SWT.V_SCROLL));
		try (Finally lock = BNAUtils.lock()) {
			parent.setLayout(new FillLayout());
			setLayout(new FillLayout());
			this.bnaUIStyle = style & ~(SWT.H_SCROLL | SWT.V_SCROLL);
			this.view = view;
			setBNAUI(new AutodetectBNAUI(view));
			updateScrollBars();
			updateCoordinateMapper();
			view.getCoordinateMapper().addCoordinateMapperListener(this);
			view.getBNAWorld().getBNAModel().addBNAModelListener(this);
			addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					try (Finally lock = BNAUtils.lock()) {
						view.getCoordinateMapper().removeCoordinateMapperListener(BNACanvas.this);
						view.getBNAWorld().getBNAModel().removeBNAModelListener(BNACanvas.this);
						if (bnaUI != null) {
							bnaUI.getComposite().removeControlListener(BNACanvas.this);
							bnaUI.getComposite().removeMouseListener(BNACanvas.this);
							bnaUI.dispose();
							bnaUI = null;
							if (disposeView) {
								view.dispose();
							}
						}
					}
				}
			});
		}
	}

	public void setBNAUI(IBNAUI bnaUI) {
		if (hBar != null && !hBar.isDisposed()) {
			hBar.removeSelectionListener(this);
		}
		if (vBar != null && !vBar.isDisposed()) {
			vBar.removeSelectionListener(this);
		}
		try (Finally lock = BNAUtils.lock()) {
			view.disposePeers();
			if (this.bnaUI != null) {
				this.bnaUI.getComposite().removeControlListener(this);
				this.bnaUI.getComposite().removeMouseListener(this);
				this.bnaUI.dispose();
			}
			this.bnaUI = bnaUI;
			bnaUI.init(this, bnaUIStyle);
		}
		layout(true, true);
		bnaUI.getComposite().addControlListener(this);
		bnaUI.getComposite().addMouseListener(this);
		hBar = getHorizontalBar();
		vBar = getVerticalBar();
		updateScrollBars();
		if (hBar != null) {
			hBar.addSelectionListener(this);
		}
		if (vBar != null) {
			vBar.addSelectionListener(this);
		}
	}

	@Override
	public void controlMoved(ControlEvent e) {
	}

	@Override
	public void controlResized(ControlEvent e) {
		updateScrollBars();
		bnaUI.paint();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		updateCoordinateMapper();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		updateCoordinateMapper();
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
		bnaUI.forceFocus();
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		bnaUI.paint();
	}

	@Override
	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		SWTWidgetUtils.async(bnaUI.getComposite(), new Runnable() {
			@Override
			public void run() {
				updateScrollBars();
			}
		});
		bnaUI.paint();
	}

	boolean isUpdatingScrollBars = false;
	boolean isUpdatingCoordinateMapper = false;

	protected void updateScrollBars() {
		if (isUpdatingCoordinateMapper) {
			return;
		}
		isUpdatingScrollBars = true;
		try (Finally lock = BNAUtils.lock()) {
			ICoordinateMapper cm = view.getCoordinateMapper();
			Rectangle client = getClientArea();
			Rectangle localBounds = cm.getLocalBounds();
			Point localOrigin = cm.getLocalOrigin();
			if (hBar != null) {
				updateScrollBar(hBar, localOrigin.x - localBounds.x, client.width, localBounds.width);
			}
			if (vBar != null) {
				updateScrollBar(vBar, localOrigin.y - localBounds.y, client.height, localBounds.height);
			}
		}
		finally {
			isUpdatingScrollBars = false;
		}
	}

	protected void updateScrollBar(ScrollBar bar, int selection, int thumb, int total) {
		// ScrollBar setValues(...) silently fails when certain constraints are violated
		thumb = Math.min(thumb, total);
		bar.setValues(Math.max(0, selection), 0, Math.max(1, total - thumb), Math.max(1, thumb),
				Math.max(1, thumb / 10), Math.max(1, thumb / 3));
	}

	protected void updateCoordinateMapper() {
		if (!isUpdatingScrollBars) {
			isUpdatingCoordinateMapper = true;
			try (Finally lock = BNAUtils.lock()) {
				IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
				if (mcm != null) {
					Rectangle localBounds = mcm.getLocalBounds();
					Point oldLocalOrigin = mcm.getLocalOrigin();
					Point newLocalOrigin = new Point(hBar != null ? hBar.getSelection() + localBounds.x
							: oldLocalOrigin.x, vBar != null ? vBar.getSelection() + localBounds.y : oldLocalOrigin.y);
					mcm.setLocalOrigin(newLocalOrigin);
				}
			}
			finally {
				isUpdatingCoordinateMapper = false;
			}
		}
	}

	public IBNAView getBNAView() {
		return view;
	}

}