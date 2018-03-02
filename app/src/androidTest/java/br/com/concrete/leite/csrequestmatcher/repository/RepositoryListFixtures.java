package br.com.concrete.leite.csrequestmatcher.repository;


import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import br.com.concretesolutions.requestmatcher.model.HttpMethod;

public class RepositoryListFixtures {
    private final RequestMatcherRule rule;

    public RepositoryListFixtures(RequestMatcherRule rule) {
        this.rule = rule;
    }

    public RepositoryListFixtures searchRepositories(String pageNumber) {
        rule.addFixture(200, "repository_list/page" + pageNumber + ".json")
                .ifRequestMatches()
                .methodIs(HttpMethod.GET)
                .pathIs("/search/repositories")
                .queriesContain("page", pageNumber)
                .queriesContain("q", "language:Java")
                .queriesContain("sort", "stars");
        return this;
    }
}
