package dashboards;

public class GraficoPadrao {
	private int qnt;
	private String nome;

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GraficoPadrao(int qnt, String nome) {
		this.qnt = qnt;
		this.nome = nome;
	}

}
