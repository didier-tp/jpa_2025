package tp.appliJpa.util;


import org.hibernate.resource.jdbc.spi.StatementInspector;

public class MyHibernateInterceptor implements StatementInspector {
    @Override
    public String inspect(String sql) {
        String sql2 = sql.replaceAll(" compt ",
                " compt" + "" + "e ");
        System.out.println("***** MyHibernateInterceptor, sql="+sql2);

        //return sql2;
        return sql;
    }
}
