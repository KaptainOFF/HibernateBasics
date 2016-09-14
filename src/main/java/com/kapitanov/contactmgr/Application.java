package com.kapitanov.contactmgr;

import com.kapitanov.contactmgr.model.Contact;
import com.kapitanov.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.service.ServiceRegistry;

import java.rmi.registry.Registry;

public class Application {
    //Hold a reusable reference to a SessionFactory
   private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("Yani","Kapitanov")
                .withEmail("my@email.com")
                .withPhone(1233444444L)
                .build();
        //Open Session
        Session session = sessionFactory.openSession();

        //Begin Transaction
        session.beginTransaction();
        //Use session to save contact
        session.save(contact);
        ///Commit transaction
        session.getTransaction().commit();
        //Close session
        session.close();
    }
}
