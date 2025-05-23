package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.MaterialWeb;


public class Consultar {
	private ObjectContainer manager;

	public Consultar() {
		manager = Util.conectarBanco();
		consultar();
		Util.desconectar();
		System.out.println("\nFim das consultas.");
	}

	public void consultar() {
		
		// 1. Quais materiais web são vídeos com nota x
		int nota = 2;
		System.out.println("\n--- Materiais Web que são vídeos com nota " + nota);
		Query q1 = manager.query();
		q1.constrain(MaterialWeb.class);
		q1.descend("tipomaterial").descend("nome").constrain("Video");
		q1.descend("nota").constrain(nota);
		List<MaterialWeb> resultados1 = q1.execute();
		for (MaterialWeb m : resultados1)
			System.out.println(m);

		// 2. Quais materiais web possuem uma certa palavra-chave
		String palavraBusca = "programacao";
		System.out.println("\n--- Materiais Web que possuem a palavra-chave '" + palavraBusca + "'");
		Query q2 = manager.query();
		q2.constrain(MaterialWeb.class);
		q2.descend("listaPalavrasChave").descend("palavra").constrain(palavraBusca);
		List<MaterialWeb> resultados2 = q2.execute();
		for (MaterialWeb m : resultados2)
			System.out.println(m);

		// 3. Quais materiais web têm mais de X palavras-chave
		int minimoPalavras = 2;
		System.out.println("\n--- Materiais Web que possuem mais de " + minimoPalavras + " palavras-chave");
		Query q3 = manager.query();
		q3.constrain(MaterialWeb.class);
		q3.constrain(new FiltroPalavras(minimoPalavras));
		List<MaterialWeb> resultados3 = q3.execute();
		for (MaterialWeb m : resultados3)
			System.out.println(m);
		
		// 4. Listar nome e título de materias web com nota X
		int notaDesejada = 4;
		System.out.println("\n--- Nome e título de materiais web com nota " + notaDesejada);

		Query q4 = manager.query();
		q4.constrain(MaterialWeb.class);
		q4.descend("nota").constrain(notaDesejada);

		List<MaterialWeb> resultados = q4.execute();

		for (MaterialWeb material : resultados) {
		    System.out.println("Título: " + material.getTitulo() + ", Tipo: " + material.getTipomaterial().getNome());
		}
		
		// 5. Consulta pelo material de maior nota de um certo tipo
		String tipoDesejado = "Video";
		System.out.println("\n--- Melhor material do tipo " + tipoDesejado + ":");
		Query q5 = manager.query();
		q5.constrain(MaterialWeb.class);
		q5.descend("tipomaterial").descend("nome").constrain("Video");
		List<MaterialWeb> materiais = q5.execute();

		// Procurar o material com maior nota de um tipo desejado
		MaterialWeb melhorMaterial = null;
		for (MaterialWeb m : materiais) {
		    if (melhorMaterial == null || m.getNota() > melhorMaterial.getNota()) {
		        melhorMaterial = m;
		    }
		}

		if (melhorMaterial != null) {
		    System.out.println("Título: " + melhorMaterial.getTitulo());
		    System.out.println("Nota: " + melhorMaterial.getNota());
		} else {
		    System.out.println("Nenhum material do tipo '" + tipoDesejado + "' foi encontrado.");
		}

	}

	public static void main(String[] args) {
		new Consultar();
	}
}

@SuppressWarnings("serial")
// Classe interna para a 3ª consulta
class FiltroPalavras implements Evaluation {
	private int minimo;

	public FiltroPalavras(int minimo) {
		this.minimo = minimo;
	}

	@Override
	public void evaluate(Candidate candidate) {
		MaterialWeb materialweb = (MaterialWeb) candidate.getObject();
		if (materialweb.getListaPalavrasChave().size() > minimo)
			candidate.include(true);
		else
			candidate.include(false);
	}
}
