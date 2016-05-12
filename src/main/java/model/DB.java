package model;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dawid on 11.05.16.
 */
//@Qualifier("dataSource")
public class DB {
    @Autowired
    @Qualifier("sessionFactory")
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    public static void main() throws Exception {
        System.out.println("jestem w main w DB");
        final Session session = getSession();

        SurveysEntity survey = new SurveysEntity("Q","W","E","R","T");
// zapis do bazy
        session.beginTransaction();
        session.save(survey);
        session.getTransaction().commit();
//        session.close();


        //odczyt z bazy przez id
        Transaction tx = session.getTransaction();
        survey = (SurveysEntity) session.get(SurveysEntity.class, 1);
        String variants = survey.getVariants();



        //odczyt poprzez select CAŁEJ TABELI
        Query query = session.createQuery("FROM SurveysEntity");


        List list = query.list();

        for (Iterator iterator =
             list.iterator(); iterator.hasNext();){
            System.out.println("===================================");
            SurveysEntity survey1 = (SurveysEntity) iterator.next();
            System.out.print(survey1);

        }


        //ODCZYT PRZEZ SELECT KONKRETNEJ WARTOSCI LUB WIERSZA
        Query query3 = session.createSQLQuery("SELECT categories from surveys where name='c'");
        String survey3 = (String) query3.list().get(1);
        System.out.println("WZIALEM CATEGOIRE Z BAZY I WYGLADAJA ONE TAK: " + survey3);
     //   Query query3 = session.createSQLQuery("SELECT categories from SurveysEntity where name='c'");



        // odczyt wybranoego elementu

//        for(SurveysEntity s : list){
//            System.out.println(s.toString());
//        }
        session.close();

//        try {
//            System.out.println("querying all the managed entities...");
//            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
//            for (Object key : metadataMap.keySet()) {
//                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//                final String entityName = classMetadata.getEntityName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
    }

}
