package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=FontData].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasMutableFontData extends IHasFontData {
	public void setDontIncreaseFontSize(boolean dontIncreaseFontSize);

	public void setFontName(java.lang.String fontName);

	public void setFontSize(int fontSize);

	public void setFontStyle(org.archstudio.swtutils.constants.FontStyle fontStyle);
}