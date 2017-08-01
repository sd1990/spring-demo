package org.songdan.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Songdan
 * @date 2017/4/12 16:31
 */
public class CustomRouterDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceManager.get();
    }
}
