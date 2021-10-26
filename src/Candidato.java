public class Candidato implements Comparable<Candidato> { 
	private int inscricao;
	private String nome;
	private int idade;
	private int nota;

	public Candidato(int inscricao, String nome, int idade, int nota) {
		this.inscricao = inscricao;
		this.nome = nome;
		this.idade = idade;
		this.nota = nota;
	}

	public int getInscricao() {
		return this.inscricao;
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	public int getNota() {
		return this.nota;
	}

    public void getCandidato() {
        System.out.println("Inscrição: " + getInscricao());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Nota: " + getNota());
        System.out.println(" ");
    }
	
	@Override
	public int compareTo(Candidato outro) { 
		if (nota != outro.getNota()) { 
			return -(nota - outro.getNota()); 
		} else { //caso possuam a mesma nota
			return -(idade - outro.getIdade()); 
		}
	}

}
