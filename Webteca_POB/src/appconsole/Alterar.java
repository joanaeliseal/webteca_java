package appconsole;

import javax.management.Query;

import com.db4o.ObjectContainer;

import modelo.MaterialWeb;

public class Alterar {
    private static ObjectContainer manager;

    public static void main(String[] args) {
        manager = Util.conectarBanco();
        System.out.println("Alterar nota do material 'Introdução à Programação' para 5");

        Query q = manager.query();
        q.constrain(MaterialWeb.class);
        q.descend("titulo").constrain("Introdução à Programação");
        MaterialWeb material = (MaterialWeb) q.execute().get(0);

        material.setNota(5);
        manager.store(material);
        manager.commit();

        Util.desconectar();
        System.out.println("Alteração concluída");
    }
}