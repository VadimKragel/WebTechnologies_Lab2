package by.bsuir.lab2.dao;

import by.bsuir.lab2.dao.mysql.MySqlDAOManager;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static final Map<Enum<StorageType>, DAOManager> managers = new HashMap<>();

    static {
        managers.put(StorageType.MY_SQL, new MySqlDAOManager());
    }
    
    private DAOFactory() {
    }
    
    public static DAOManager getDAOManager(StorageType storageType) {
        DAOManager daoManager = managers.get(storageType);
        if (daoManager == null) {
            daoManager = managers.get(StorageType.MY_SQL);
        }
        return daoManager;
    }
}
