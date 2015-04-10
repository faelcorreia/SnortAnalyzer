package dashboards;

import java.util.ArrayList;
import banco.*;

public class Top10Categorias {
	private ArrayList<Categoria> categorias;
	
	public Top10Categorias() {
		categorias = Consultas.getTop10Categorias();
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
}
