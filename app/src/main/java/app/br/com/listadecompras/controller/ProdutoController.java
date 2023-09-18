package app.br.com.listadecompras.controller;

import android.util.Log;

import java.util.List;

import app.br.com.listadecompras.model.Produto;
import io.realm.Realm;

public class ProdutoController implements ICrud<Produto>{
    @Override
    public void insert(Produto obj) {

        Realm realm = Realm.getDefaultInstance();

        Number primaryKey = realm.where(Produto.class).max("id");

        final int autoIncrementPrimaryKey = (primaryKey == null) ? 1 : primaryKey.intValue() +1;

        obj.setId(autoIncrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();

        Log.d("db_log","insert: "+obj.getId());

    }

    @Override
    public void update(Produto obj) {

        Realm realm = Realm.getDefaultInstance();

        Produto produto = realm.where(Produto.class).equalTo("id", obj.getId()).findFirst();

        if (produto != null){

            realm.beginTransaction();

            produto.setDataDaInclusao(obj.getDataDaInclusao());
            produto.setNomeDoProduto(obj.getNomeDoProduto());
            produto.setUnidadeDeMedida(obj.getUnidadeDeMedida());
            produto.setQuantidade(obj.getQuantidade());
            produto.setPrecoPago(obj.getPrecoPago());
            produto.setCodigoDeBarras(obj.getCodigoDeBarras());
            produto.setImagem(obj.getImagem());
            realm.commitTransaction();

        }
        realm.close();

    }

    @Override
    public void delete(Produto obj) {

    }

    @Override
    public void deleteByID(Produto obj) {

    }

    @Override
    public List<Produto> listar() {
        return null;
    }
}
