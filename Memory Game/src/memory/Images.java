package memory;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class Images {

	private class Item {
		// INSTANCE VARIABLES
		Integer intCod;
		String strNomeRecurso;

		// CONSTRUCTOR
		Item(Integer intCod, String strNomeRecurso) {
			this.intCod = intCod;
			this.strNomeRecurso = strNomeRecurso;
		}
	}

	// INSTANCE VARIABLE
	private final Map<Integer, Item> mapa;

	// CONSTRUCTOR
	public Images() {
		mapa = new HashMap<>();
		preenche();
	}

	// GETTER
	public String getResourceName(Integer intCod) {
		return mapa.get(intCod).strNomeRecurso;
	}
	
	/*
	 * This method returns the images to be used
	 */
	public ImageIcon IconFactory(Integer intCod) {
		if (!mapa.containsKey(intCod)) {
			System.out.println("IconFactory problem");
			return null;
		}
		return new ImageIcon(getClass().getClassLoader().getResource(getResourceName(intCod)));
	}
	
	/*
	 * This method creates each individual instance of images to be manipulated 
	 */
	private void preenche() {
		Item item;
		int i = -1;

		// undiscovered image
		item = new Item(i++, "images/pokeball.png");
		mapa.put(item.intCod, item);

		// discovered image
		item = new Item(i++, "images/caught.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/appletun.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/carkol.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/cinderace.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/dracovish.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/dragapult.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/eiscue.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/cramorant.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/sirfetchd.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/toxtricity.png");
		mapa.put(item.intCod, item);

		item = new Item(i++, "images/zacian.png");
		mapa.put(item.intCod, item);

	}

}
