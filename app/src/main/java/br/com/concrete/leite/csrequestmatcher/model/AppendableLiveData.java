package br.com.concrete.leite.csrequestmatcher.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.Collection;


public class AppendableLiveData<T extends Collection> extends MutableLiveData<ResponseData<T>> {

    @SuppressWarnings("unchecked")
    public void setAppendData(T collection){
        if(this.getValue() == null){
            this.setValue(ResponseData.success(collection));
        }
        else {
            Collection mergedCollection = this.getValue().data;
            mergedCollection.addAll(collection);

            this.setValue(ResponseData.success((T) mergedCollection));
        }
    }

}
