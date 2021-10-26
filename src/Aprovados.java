import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Aprovados {
	
	static int NumeroDeVagas = 100;
	
	public static void ListarAprovados (List<Candidato> lista) {

		for (int i = 0; i < NumeroDeVagas; i++) { 
			Candidato chave = lista.get(i);
			System.out.println("Posição: "+ String.valueOf(i+1));
			chave.getCandidato();
		}

	}

	public static void GerarArquivo(List<Candidato> lista) throws IOException {

		FileWriter arq = new FileWriter("./arquivos/aprovados.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		for (int i = 0; i < NumeroDeVagas; i++) { 
			Candidato chave = lista.get(i);
			gravarArq.printf(String.valueOf(i+1) + ";" + chave.getInscricao() + ";" + chave.getNome() + ";" + chave.getIdade() + ";"
					+ chave.getNota() + "\n");
		}

		arq.close();

	}
}
