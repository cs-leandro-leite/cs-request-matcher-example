package br.com.concrete.leite.csrequestmatcher.data.mapper;



import java.util.ArrayList;
import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.model.RepositoryDataModel;
import br.com.concrete.leite.csrequestmatcher.model.Repository;
import br.com.concrete.leite.csrequestmatcher.model.User;


public class RepositoryDataModelMapper implements DataModelMapper<Repository, RepositoryDataModel> {

    private UserDataModelMapper userDataModelMapper;

    public RepositoryDataModelMapper() {
        userDataModelMapper = new UserDataModelMapper();
    }

    @Override
    public Repository toModel(RepositoryDataModel dataModel) {
        Repository repository = new Repository();
        User owner = userDataModelMapper.toModel(dataModel.getOwner());

        repository.setOwner(owner);
        repository.setName(dataModel.getName());
        repository.setFullName(dataModel.getFullName());
        repository.setUrl(dataModel.getUrl());
        repository.setDescription(dataModel.getDescription());
        repository.setForks(dataModel.getForks());
        repository.setStars(dataModel.getStars());
        repository.setIssues(dataModel.getIssues());

        return repository;
    }

    @Override
    public List<Repository> toModelList(List<RepositoryDataModel> dataModelList){
        List<Repository> repositories = new ArrayList<>();

        for(RepositoryDataModel dataModel : dataModelList){
            Repository repository = toModel(dataModel);
            repositories.add(repository);
        }

        return repositories;
    }

    @Override
    public RepositoryDataModel toDataModel(Repository entity) {
        return null;
    }

    @Override
    public List<RepositoryDataModel> toDataModelList(List<Repository> entity) {
        return null;
    }
}
