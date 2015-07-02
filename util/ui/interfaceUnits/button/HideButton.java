package org.util.ui.interfaceUnits.button;

import java.awt.Image;

import org.Context;
import org.powerbot.script.AbstractScript;
import org.util.ui.Dimension;
import org.util.ui.UI;

public class HideButton extends Button {
	Image minimizeImg, maximizeImg;
			
	public HideButton(Dimension plane, UI container) {
		super(plane, container);
		
	}

	@Override
	public void onAction() {
		if(getPanel() != null){
			getPanel().show(!getPanel().isShown());
			if(getPanel().isShown()){
				setImage(minimizeImg);
			} else {
				setImage(maximizeImg);
			}
		}
	}

	@Override
	public void load(AbstractScript<Context> script) {
		minimizeImg = script.downloadImage("http://imageshack.us/a/img401/1615/10lo.png");
		maximizeImg = script.downloadImage("http://imageshack.us/a/img835/8821/ysoc.png");
		setImage(minimizeImg);
	}
}
