package totoro.application.xkf.totoroweather.application;

import totoro.application.xkf.totoroweather.service.DataService;

public class AppCache {
    private static DataService sDataService;

    public static void setDataService(DataService dataService) {
        sDataService = dataService;
    }

    public static DataService getDataService() {
        return sDataService;
    }
}
