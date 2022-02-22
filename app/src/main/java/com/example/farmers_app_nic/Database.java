package com.example.farmers_app_nic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection connection;


    // For Local PostgreSQL
    private final String host = "10.0.2.2";

    public final String database = "farmer_database";
    public final int port = 5432;
    public final String user = "postgres";
    public final String pass = "1234";
    public String url = "jdbc:postgresql://%s:%d/%s";
    public boolean status;

    public Database()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);

                    if (connection!=null){
                    status = true;
                    System.out.println("connected:" + status);
                        Statement st ;
                        System.out.println("true1 ");
                        st = connection.createStatement();
                        String sql;
                        System.out.println("true 2");
                        sql = "SELECT * FROM crop_nature_details;";
                        ResultSet rs = st.executeQuery(sql);
                        System.out.println("true3 ");

                        while(rs.next()){
                            System.out.println("true"+rs.getString(2));

                        }
                        System.out.println("true5 ");
                        rs.close();
                        st.close();


                    }
                    else {
                        System.out.println("not connected:" + status);}


                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);




        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}
