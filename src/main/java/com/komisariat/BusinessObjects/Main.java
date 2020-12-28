package com.komisariat.BusinessObjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List shifts = session.createQuery("FROM Shift").list();
        for(Iterator iterator = shifts.iterator(); iterator.hasNext();) {
            Shift shift = (Shift) iterator.next();
            System.out.println("\n\nID: " + shift.getId());
            System.out.println("Start: " + shift.getStartDate());
            System.out.println("End: " + shift.getEndDate());
            System.out.println("Region_ID: " + shift.getPatrolRegion().getId());
            System.out.println("Report content: " + shift.getReport().getContent());
            System.out.println("Kit name: " + shift.getKit().getName());
            System.out.println("Officer name: " + shift.getOfficer().getFirstName() + " " + shift.getOfficer().getLastName());
            System.out.println("Vehicle name: " + shift.getVehicle().getManufacturer() + " " + shift.getVehicle().getModel());
        }
        t.commit();
    }
}
