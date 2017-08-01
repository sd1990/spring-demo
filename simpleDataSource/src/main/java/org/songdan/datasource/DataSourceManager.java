package org.songdan.datasource;

/**
 * @author Songdan
 * @date 2017/4/17 10:12
 */
public class DataSourceManager {

    public static ThreadLocal<DataSources> resources = new ThreadLocal<DataSources>() {

        @Override
        protected DataSources initialValue() {
            return DataSources.CUSTOMER;
        }
    };

    public static DataSources get() {
        return resources.get();
    }

    public static void set(DataSources dataSources) {
        resources.set(dataSources);
    }

}
