package dashboards;

public class Categoria {
	private int qnt;
	private String descricao;
	
	public Categoria(int qnt, String descricao) {
		this.qnt = qnt;
		this.descricao = descricao;
	}

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
