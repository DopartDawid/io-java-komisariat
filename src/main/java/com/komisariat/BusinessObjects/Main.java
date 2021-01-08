package com.komisariat.BusinessObjects;

import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;
import com.komisariat.MainControllers.App;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
