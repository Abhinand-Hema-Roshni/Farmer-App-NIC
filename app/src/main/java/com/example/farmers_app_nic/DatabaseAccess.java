package com.example.farmers_app_nic;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;


    DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }


    public static DatabaseAccess getInstance(Context context){
        if(instance== null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!= null){
            this.db.close();
        }
    }
   public ArrayList<String> getDistrict(){
       Cursor c1 = db.rawQuery("select dname from district",new String[]{});
       ArrayList<String> result = new ArrayList<>();
       while(c1.moveToNext()){
              result.add(c1.getString(0));
               }
               c1.close();
              return result;

   }
    public ArrayList<String> getMandalfromDistrict(String district_name){

        Cursor c1 = db.rawQuery("select mname from mandal inner join district on district.dcode = mandal.dcode where dname ='"+district_name+"'",new String[]{});
        ArrayList<String> result = new ArrayList<>();
        while(c1.moveToNext()){
            result.add(c1.getString(0));
        }
        c1.close();
        return result;

    }


    public ArrayList<String> getVillagefromMandal(String mandal_name){
        Cursor c1 = db.rawQuery("select vname from village inner join mandal on mandal.mcode = village.mcode where mname ='"+mandal_name+"'",new String[]{});
        ArrayList<String> result = new ArrayList<>();
        while(c1.moveToNext()){
            result.add(c1.getString(0));
        }
        c1.close();
        return result;
    }


}


//    int userSemester1 = Integer.parseInt(String.valueOf(userSemester.charAt(userSemester.length()-1)));
//    Cursor c1 = db.rawQuery("select subject_name,no_of_hours from '"+userDepartment +"' where sem_offered" +
//            " = '"+userSemester1 +"'",new String[]{});
//    ArrayList<BunkItem> result = new ArrayList<>(10);
//        while(c1.moveToNext()){
//                result.add(new BunkItem(c1.getString(0),c1.getString(1)));
//                }
//                c1.close();
//                return result;