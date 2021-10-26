import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class App {

	public static void main(String[] args) {

		List<Candidato> arquivo_candidatos;
		arquivo_candidatos = readTXTfile("./arquivos/template.txt"); 

		JFileChooser fileChooser = new JFileChooser("./arquivos");
		FileFilter filter1 = new ExtensionFileFilter("Arquivos .txt", new String[] { "TXT" }); 
		fileChooser.setFileFilter(filter1);

		int status = fileChooser.showOpenDialog(null);
		File selectedFile;

		if (status == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile(); 
			arquivo_candidatos = readTXTfile(selectedFile.getAbsolutePath());
		} else if (status == JFileChooser.CANCEL_OPTION) {
			System.out.println(JFileChooser.CANCEL_OPTION);
		}

		Collections.sort(arquivo_candidatos); 
		
		Aprovados.ListarAprovados(arquivo_candidatos); 
		
		try {
			Aprovados.GerarArquivo(arquivo_candidatos); 
		} catch (IOException e) {
			System.out.println("Erro: Houve algum erro na gravação da lista de aprovados \n");
			e.printStackTrace();
		}

	}

	public static List<Candidato> readTXTfile(String path) {

		String Str;
		List<Candidato> tableLine = new ArrayList<Candidato>();
		
		try {
			
			BufferedReader strR = new BufferedReader(new FileReader(path));
			
			while ((Str = strR.readLine()) != null) {
				
				String[] row = Str.split(";");
				tableLine.add(new Candidato(Integer.parseInt(row[0]), row[1], Integer.parseInt(row[2]), Integer.parseInt(row[3])));
			}
			strR.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro: Arquivo não encontrado \n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro: Arquivo inválido \n");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erro: Arquivo mal formatado \n");
			e.printStackTrace();
		}
		return tableLine;
	}

}


class ExtensionFileFilter extends FileFilter {
	String description;
	String extensions[];

	public ExtensionFileFilter(String description, String extension) {
		this(description, new String[] { extension });
	}

	public ExtensionFileFilter(String description, String extensions[]) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
		this.extensions = (String[]) extensions.clone();
		toLower(this.extensions);
	}

	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	public String getDescription() {
		return description;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}
