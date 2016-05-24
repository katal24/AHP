package model;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;

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
            Configuration configuration = new AnnotationConfiguration();
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

    public boolean exists(User user){
        System.out.println("Sprawdzam czy jest taki user");
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("select 1 from User u where u.username = :name and u.password = :pass").setString("name", user.getUsername()).setString("pass", user.getPassword());

        return (query.uniqueResult() != null);

    }

    public void saveSurvay(SurveysEntity survey){
        System.out.println("ZAPISUJE DO BAZY");
        final Session session = getSession();
        session.beginTransaction();
        session.save(survey);
        session.getTransaction().commit();
        session.close();
        System.out.println("ZAPISALEM DO BAZY");

    }

    public void saveUser(User user){
        System.out.println("ZAPISUJE DO BAZY usera");
        final Session session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("ZAPISALEM DO BAZY nowego usera");

    }

    public ArrayList<SurveysEntity> getPublicSurvey(){
        System.out.println("bede pobieral z BAZY publiczne");
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from surveys where type='public'").addEntity(SurveysEntity.class);
        ArrayList<SurveysEntity> list = (ArrayList<SurveysEntity>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY publiczne");

        return list;
    }

    public ArrayList<String> getNamePublicSurvey(){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT name from surveys where type='public'");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();

        System.out.println("POBRAŁEM Z BAZU LISTE NAZW ANKIET PUBLICZNYCH");

        return namesList;
    }


    public ArrayList<String> getNameOwnerSurvey(String user){
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT name from surveys where owner='"+user+"'");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY NAZWY ANKIET dla danego usera");

        return namesList;
    }


    public ArrayList<SurveysEntity> getOwnerSurvey(String user){
        System.out.println("bede pobieral z BAZY dla danego usera");
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from surveys where owner='"+user+"'").addEntity(SurveysEntity.class);
        ArrayList<SurveysEntity> list = (ArrayList<SurveysEntity>) query.list();
        session.close();
        System.out.println("POBRALEM Z BAZY dla danego usera");

        return list;
    }

    public SurveysEntity getSurveyForName(String name){
        System.out.println("bede pobieral z BAZY dla danej nazwy");
        final Session session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from surveys where name='"+name+"'").addEntity(SurveysEntity.class);
        SurveysEntity entity = (SurveysEntity) query.list().get(0);
        session.close();
        System.out.println("POBRALEM Z BAZY ankiete dla danej nazwy");

        return entity;
    }
//
//    public static void main() throws Exception {
//        System.out.println("jestem w main w DB");
//        final Session session = getSession();
//
//        SurveysEntity survey = new SurveysEntity("Q","W","E","R","T");
//// zapis do bazy
//        session.beginTransaction();
//        session.save(survey);
//        session.getTransaction().commit();
////        session.close();
//
//
//        //odczyt z bazy przez id
//        Transaction tx = session.getTransaction();
//        survey = (SurveysEntity) session.get(SurveysEntity.class, 1);
//        String variants = survey.getVariants();
//
//
//
//        //odczyt poprzez select CAŁEJ TABELI
//        Query query = session.createQuery("FROM SurveysEntity");
//
//
//        List list = query.list();
//
//        for (Iterator iterator =
//             list.iterator(); iterator.hasNext();){
//            System.out.println("===================================");
//            SurveysEntity survey1 = (SurveysEntity) iterator.next();
//            System.out.print(survey1);
//
//        }
//
//
//        //ODCZYT PRZEZ SELECT KONKRETNEJ WARTOSCI LUB WIERSZA
//        Query query3 = session.createSQLQuery("SELECT categories from surveys where name='c'");
//        String survey3 = (String) query3.list().get(1);
//        System.out.println("WZIALEM CATEGOIRE Z BAZY I WYGLADAJA ONE TAK: " + survey3);
//     //   Query query3 = session.createSQLQuery("SELECT categories from SurveysEntity where name='c'");
//
//
//
//        // odczyt wybranoego elementu
//
////        for(SurveysEntity s : list){
////            System.out.println(s.toString());
////        }
//        session.close();
//
////        try {
////            System.out.println("querying all the managed entities...");
////            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
////            for (Object key : metadataMap.keySet()) {
////                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
////                final String entityName = classMetadata.getEntityName();
////                final Query query = session.createQuery("from " + entityName);
////                System.out.println("executing: " + query.getQueryString());
////                for (Object o : query.list()) {
////                    System.out.println("  " + o);
////                }
////            }
////        } finally {
////            session.close();
////        }
//    }

}
