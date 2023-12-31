package app.br.com.listadecompras.controller;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.br.com.listadecompras.model.Categoria;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class CategoriaController implements ICrud<Categoria> {
    @Override
    public void insert(Categoria obj) {

        Realm realm = Realm.getDefaultInstance();

        Number primaryKey = realm.where(Categoria.class).max("id");

        final int autoIncrementPrimaryKey = (primaryKey == null) ? 1 : primaryKey.intValue() +1;

        obj.setId(autoIncrementPrimaryKey);

        realm.beginTransaction();
        realm.copyToRealm(obj);
        realm.commitTransaction();
        realm.close();

        Log.d("db_log","insert: "+obj.getId());

    }

    @Override
    public void update(Categoria obj) {

        Realm realm = Realm.getDefaultInstance();

        Categoria categoria = realm.where(Categoria.class).equalTo("id", obj.getId()).findFirst();

        if (categoria != null){

            realm.beginTransaction();

            categoria.setNomeDaCategoria(obj.getNomeDaCategoria());
            categoria.setTotalDeProdutos(obj.getTotalDeProdutos());
            categoria.setImagemCategoria(obj.getImagemCategoria());
            realm.commitTransaction();

        }
        realm.close();

    }

    @Override
    public void delete(Categoria obj) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        RealmResults<Categoria> results = realm.where(Categoria.class).equalTo("id",
                obj.getId()).findAll();

        results.deleteAllFromRealm();

        realm.commitTransaction();

        realm.close();

    }

    @Override
    public void deleteByID(int id) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        RealmResults<Categoria> results = realm.where(Categoria.class).equalTo("id",
                id).findAll();

        results.deleteAllFromRealm();

        realm.commitTransaction();

        realm.close();

    }

    @Override
    public List<Categoria> listar() {

        Realm realm = null;

        RealmResults<Categoria> results = null;

        List<Categoria> list = new ArrayList<>();

        try{

            realm = Realm.getDefaultInstance();

            results = realm.where(Categoria.class).findAll();

            list = realm.copyFromRealm(results);

        }catch (RealmException e){

        }finally {
            realm.close();
        }


        return  list;

    }
}
