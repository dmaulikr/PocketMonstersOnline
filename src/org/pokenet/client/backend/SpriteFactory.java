package org.pokenet.client.backend;

import java.util.HashMap;

import mdes.slick.sui.Label;
import mdes.slick.sui.skin.ImageUIResource;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;
import org.pokenet.client.GameClient;
import org.pokenet.client.backend.entity.Player.Direction;

/**
* Handles overworld sprites
* @author shinobi
*
*/
public class SpriteFactory {
	private HashMap<Integer, SpriteSheet> spriteSheets;
	
	/**
	 * Returns the requested sprite
	 * @param dir
	 * @param isMoving
	 * @param isLeftFoot
	 * @param sprite
	 * @return
	 */
	public Image getSprite(Direction dir, boolean isMoving, 
			boolean isLeftFoot, int sprite) {
		SpriteSheet sheet = spriteSheets.get(sprite);
		if (isMoving) {
			if (isLeftFoot) {
				switch (dir) {
				case Up:
					return sheet.getSprite(0, 0);
				case Down:
					return sheet.getSprite(0, 2);
				case Left:
					return sheet.getSprite(0, 3);
				case Right:
					return sheet.getSprite(0, 1);
				}
			} else {
				switch (dir) {
				case Up:
					return sheet.getSprite(2, 0);
				case Down:
					return sheet.getSprite(2, 2);
				case Left:
					return sheet.getSprite(2, 3);
				case Right:
					return sheet.getSprite(2, 1);
				}
			}
		} else {
			switch (dir) {
			case Up:
				return sheet.getSprite(1, 0);
			case Down:
				return sheet.getSprite(1, 2);
			case Left:
				return sheet.getSprite(1, 3);
			case Right:
				return sheet.getSprite(1, 1);
			}
		}
		return null;
	}
	
	/**
	 * Initialises the database of sprites
	 */
	public SpriteFactory() {		

		spriteSheets = new HashMap<Integer, SpriteSheet>();	
		try {
			String location;
			String respath = System.getProperty("res.path");
			if(respath==null)
				respath="";
			
			Image[] imgArray = new Image[230];
				// we have to do the loops seperately, because making a spritesheet messes
				// up deferred loading
				// but this way uses a lot more ram.... sad face
				for (int i = 0; i < 224 + 5; i++){
					
						imgArray[i] = new Image("res/characters/" + i + ".png");
					
				}
			
			/*
			 * WARNING: Change 224 to the amount of sprites we have in client
			 */
				for(int i = -5; i < 224; i++) {
//						location = respath+"res/characters/" + String.valueOf(i) + ".png";
					if(imgArray[i + 5] != null){
						spriteSheets.put(i, new SpriteSheet(imgArray[i + 5], 41, 51));//new Image(location), 41, 51));
					}
	
				}
		} catch (Exception e) { 
			System.err.println(e.getMessage());
		}
	}
	
	
}
