package appconsole;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.MaterialWeb;
import modelo.PalavraChave;
import modelo.TipoMaterial;

public class Util {
    private static ObjectContainer manager;

    public static ObjectContainer conectarBanco(){
        if (manager != null)
            return manager;

		//---------------------------------------------------------------
		//configurar, criar e abrir banco local (pasta do projeto)
		//---------------------------------------------------------------
        EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
        config.common().messageLevel(0);
        
    	// habilitar cascata na alteração, remoção e leitura
        config.common().objectClass(MaterialWeb.class).cascadeOnUpdate(true);
        config.common().objectClass(MaterialWeb.class).cascadeOnActivate(true);
        config.common().objectClass(PalavraChave.class).cascadeOnUpdate(true);
        config.common().objectClass(PalavraChave.class).cascadeOnActivate(true);
        config.common().objectClass(TipoMaterial.class).cascadeOnUpdate(true);
        config.common().objectClass(TipoMaterial.class).cascadeOnActivate(true);

        manager = Db4oEmbedded.openFile(config, "webbiblioteca.db4o");
        return manager;
    }
    
    public static void desconectar() {
        if(manager!=null) {
            manager.close();
            manager=null;
        }
    }
}