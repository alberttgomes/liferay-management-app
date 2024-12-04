package com.management.app.internal.search.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = "indexer.class.name=com.management.app.model.Employee",
    service = KeywordQueryContributor.class
)
public class EmployeeKeywordQueryContributor
        implements KeywordQueryContributor {

    @Override
    public void contribute(
        String keyword, BooleanQuery booleanQuery,
        KeywordQueryContributorHelper keywordQueryContributorHelper) {

        _queryHelper.addSearchTerm(
                booleanQuery, keywordQueryContributorHelper.getSearchContext(),
                "employeeId", false);

        _queryHelper.addSearchTerm(
                booleanQuery, keywordQueryContributorHelper.getSearchContext(),
                "firstName", false);

        _queryHelper.addSearchTerm(
                booleanQuery, keywordQueryContributorHelper.getSearchContext(),
                "lastName", false);

        _queryHelper.addSearchLocalizedTerm(
                booleanQuery, keywordQueryContributorHelper.getSearchContext(),
                "department", false);
    }

    @Reference
    private QueryHelper _queryHelper;

}
