package appconsole;

import com.db4o.ObjectContainer;

import modelo.MaterialWeb;
import modelo.PalavraChave;
import modelo.TipoMaterial;


public class Cadastrar {
	private static ObjectContainer manager;
	
	public static void main(String[] args) {
		manager = Util.conectarBanco();
		
		TipoMaterial video = new TipoMaterial("video");
		TipoMaterial site = new TipoMaterial("site");
		manager.store(video);
		manager.store(site);
		manager.commit();
		
		// Cadastrar palavras-chave
		PalavraChave programacao = new PalavraChave("programacao"); 
		PalavraChave calculo = new PalavraChave("calculo"); 
		PalavraChave java = new PalavraChave("java");
		
		manager.store(programacao);
		manager.store(calculo);
		manager.store(java);
		manager.commit();

		MaterialWeb material1 = new MaterialWeb("https://youtube.com/video1","Introdução à Programação",video,4);
		material1.adicionarPalavraChave(programacao);
        manager.store(material1);
        
		manager.store(material1);
		manager.commit();
		
		Util.desconectar();
		System.out.println("cadastrou as lojas, gerentes e empregados");
	}

}