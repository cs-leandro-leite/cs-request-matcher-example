package br.com.concrete.leite.csrequestmatcher.data.mapper;


import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.model.DataModel;
import br.com.concrete.leite.csrequestmatcher.model.Model;


public interface DataModelMapper<T extends Model, U extends DataModel> {
    T toModel(U dataModel);
    List<T> toModelList(List<U> dataModels);
    U toDataModel(T model);
    List<U> toDataModelList(List<T> models);
}
