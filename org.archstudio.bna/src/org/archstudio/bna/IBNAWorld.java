package org.archstudio.bna;

public interface IBNAWorld {

	public Object getID();

	public void dispose();

	public boolean isDestroyed();

	public IBNAModel getBNAModel();

	public IThingLogicManager getThingLogicManager();
}
