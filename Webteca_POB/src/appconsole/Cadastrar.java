package appconsole;
import com.db4o.ObjectContainer;

import modelo.TipoMaterial;
import modelo.PalavraChave;
import modelo.MaterialWeb;


public class Cadastrar {
	private static ObjectContainer manager;
	
	public static void main(String[] args) {
		manager = Util.conectarBanco();
		
		TipoMaterial livro = new TipoMaterial("Livro"); 
		
		manager.store(livro);
		manager.commit();
		
		
		TipoMaterial video = new TipoMaterial("Video"); 
		
		manager.store(video);
		manager.commit();
		
		TipoMaterial site = new TipoMaterial("Site"); 
		
		manager.store(site);
		manager.commit();
		
		TipoMaterial apostila = new TipoMaterial("Apostila"); 
		
		manager.store(apostila);
		manager.commit();
		
		TipoMaterial slide = new TipoMaterial("Slide"); 
		
		manager.store(slide);
		manager.commit();
		
		PalavraChave programacao = new PalavraChave("programacao");
		
		manager.store(programacao);
		manager.commit();
		
		PalavraChave computacao = new PalavraChave("computacao");
		
		manager.store(computacao);
		manager.commit();
		
		PalavraChave matematica = new PalavraChave("matematica");
		
		manager.store(matematica);
		manager.commit();
		
		MaterialWeb introd_prog = new MaterialWeb("www.prog.com","Introdução a programação", video, 4 ); 
		introd_prog.adicionar(programacao);	
		introd_prog.adicionar(new PalavraChave("iniciante"));
		introd_prog.adicionar(computacao);
			
		manager.store(introd_prog);
		manager.commit();
		
		MaterialWeb fund_comp = new MaterialWeb("www.funds.com","Fundamentos da computação", apostila, 3 ); 
		fund_comp.adicionar(new PalavraChave("fundamentos"));	
		fund_comp.adicionar(new PalavraChave("softwares"));
		fund_comp.adicionar(computacao);
			
		manager.store(fund_comp);
		manager.commit();
		
		MaterialWeb estatistica = new MaterialWeb("www.statistics.com","Estatística básica", slide, 4 ); 
		estatistica.adicionar(new PalavraChave("media"));	
		estatistica.adicionar(new PalavraChave("graficos"));
		estatistica.adicionar(matematica);
			
		manager.store(estatistica);
		manager.commit();
		
		MaterialWeb calculo = new MaterialWeb("www.math.com","Cálculo I", livro, 1 ); 
		calculo.adicionar(matematica);
			
		manager.store(calculo);
		manager.commit();
		
		MaterialWeb probabilidade = new MaterialWeb("www.probability.com","Probabilidade Avançada", video, 2 ); 
		probabilidade.adicionar(new PalavraChave("evento"));	
		probabilidade.adicionar(matematica);
			
		manager.store(probabilidade);
		manager.commit();
		
		MaterialWeb pweb = new MaterialWeb("www.angular.com","Programação para Web", site, 4 ); 
		pweb.adicionar(new PalavraChave("angular"));	
		pweb.adicionar(new PalavraChave("web"));	
		pweb.adicionar(programacao);
			
		manager.store(pweb);
		manager.commit();
		
		MaterialWeb pob = new MaterialWeb("www.pob.com","Persistência de objetos", video, 5 ); 
		pob.adicionar(new PalavraChave("objeto"));	
		pob.adicionar(new PalavraChave("bd"));	
		pob.adicionar(new PalavraChave("persistencia"));	
		pob.adicionar(new PalavraChave("grafo"));	
	
			
		manager.store(pob);
		manager.commit();
		
		Util.desconectar();
		System.out.println("Cadastrou os materias, seus tipos e as palavras chaves!");
	}

}
