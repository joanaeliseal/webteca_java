package appconsole;

import java.util.List;

import javax.management.Query;

import com.db4o.ObjectContainer;

import modelo.MaterialWeb;

public class Consultar {
    private static ObjectContainer manager;

    public static void main(String[] args) {
        manager = Util.conectarBanco();
        
        System.out.println("\n--- Materiais com nota 5 ---");
        Query q = manager.query();
        q.constrain(MaterialWeb.class);
        q.descend("nota").constrain(5);
        List<MaterialWeb> resultados = q.execute();
        resultados.forEach(System.out::println);

        System.out.println("\n--- Vídeos sobre programação ---");
        q = manager.query();
        q.constrain(MaterialWeb.class);
        q.descend("tipoMaterial").descend("nome").constrain("VIDEO");
        q.descend("palavrasChave").descend("palavra").constrain("programacao");
        resultados = q.execute();
        resultados.forEach(System.out::println);

        Util.desconectar();
    }
}