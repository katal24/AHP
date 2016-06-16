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
    Session session;

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
        session = getSession();

        session.beginTransaction();
        Query query = session.createQuery("select 1 from User u where u.username = :name and u.password = :pass").setString("name", user.getUsername()).setString("pass", user.getPassword());

        return (query.uniqueResult() != null);

    }

    public void saveSurvay(SurveysEntity survey){
        session = getSession();
        session.beginTransaction();
        session.save(survey);
        session.getTransaction().commit();
        session.close();
    }

    public void saveUser(User user){
        session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public ArrayList<SurveysEntity> getPublicSurvey(){
        session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT * from surveys where type='public'").addEntity(SurveysEntity.class);
        ArrayList<SurveysEntity> list = (ArrayList<SurveysEntity>) query.list();
        session.close();

        return list;
    }

    public ArrayList<String> getNamePublicSurvey(){
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT name from surveys where type='public'");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();

        return namesList;
    }


    public ArrayList<String> getNameOwnerSurvey(String user){
        final Session session = getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT name from surveys where owner='"+user+"' and type='private'");
        ArrayList<String> namesList = (ArrayList<String>) query.list();
        session.close();

        return namesList;
    }


    public ArrayList<SurveysEntity> getOwnerSurvey(String user){
        session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from surveys where owner='"+user+"'").addEntity(SurveysEntity.class);
        ArrayList<SurveysEntity> list = (ArrayList<SurveysEntity>) query.list();
        session.close();

        return list;
    }

    public SurveysEntity getSurveyForName(String name){
        session = getSession();
        session.beginTransaction();

        Query query = session.createSQLQuery("SELECT * from surveys where name='"+name+"'").addEntity(SurveysEntity.class);
        SurveysEntity entity = (SurveysEntity) query.list().get(0);
        session.close();

        return entity;
    }

}
