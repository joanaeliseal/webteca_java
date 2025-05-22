package modelo;

import java.util.ArrayList;

public class PalavraChave {
    private String palavra;
    private ArrayList<MaterialWeb> listaMateriais = new ArrayList<>();

    public PalavraChave (String palavra) {
        this.palavra = palavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;

    }

    public ArrayList<MaterialWeb> getListaMateriais() {
        return listaMateriais;
    }

    public void adicionarMaterial(MaterialWeb m) {
    if (!listaMateriais.contains(m)) {
        listaMateriais.add(m);
        m.getListaPalavrasChave().add(this);  
    }
}

    public void removerMaterial(MaterialWeb m) {
        if (listaMateriais.contains(m)) {
            listaMateriais.remove(m);
            m.getListaPalavrasChave().remove(this);
        }
    }

    @Override
    public String toString() {
        return "palavra = " + palavra;
    }
   
}
