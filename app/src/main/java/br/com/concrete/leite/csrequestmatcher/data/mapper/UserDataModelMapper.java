package br.com.concrete.leite.csrequestmatcher.data.mapper;


import java.util.List;

import br.com.concrete.leite.csrequestmatcher.data.model.UserDataModel;
import br.com.concrete.leite.csrequestmatcher.model.User;


public class UserDataModelMapper implements DataModelMapper<User, UserDataModel> {

    @Override
    public User toModel(UserDataModel dataModel) {
        User user = new User();

        user.setId(dataModel.getId());
        user.setUsername(dataModel.getUsername());
        user.setName(dataModel.getName());
        user.setAvatarUrl(dataModel.getAvatarUrl());
        user.setLocation(dataModel.getLocation());
        user.setWebsiteUrl(dataModel.getWebsiteUrl());
        user.setAmountRepositories(dataModel.getAmountRepositories());

        return user;
    }

    @Override
    public List<User> toModelList(List<UserDataModel> dataModels) {
        return null;
    }

    @Override
    public UserDataModel toDataModel(User entity) {
        return null;
    }

    @Override
    public List<UserDataModel> toDataModelList(List<User> entity) {
        return null;
    }
}
