package com.example.tapos.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tapos on 1/22/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DB_FILE_NAME = "concretepage";
    public DatabaseHandler(Context context) {
        super(context, DB_FILE_NAME, null, DATABASE_VERSION);
    }
    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE album_info ( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR2(30), " +
                " class_name VARCHAR2(20)," +
                " age INT,"+
                " city VARCHAR2(30) )";
        db.execSQL(sql);

        insertDummyData();
    }
    private void insertDummyData() {
        List<Album> albumList = new ArrayList<>();
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);





    }


    //Update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion + 1) {
            //	db.execSQL("ALTER TABLE student_info ADD COLUMN country VARCHAR(30)");
        }
    }
    //Insert data into table
    public void insertData(Album album){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO album_info (name, age, class_name, city) " +
                "VALUES (?,?,?,?)");
        stmt.bindString(1, album.getName());
        stmt.bindLong(2, album.getAge());
        stmt.bindString(3, album.getClassName());
        stmt.bindString(4, album.getCity());
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Update data into table
    public void updateData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("UPDATE student_info SET name=?, age=?, class_name=?, city=? "+
                "WHERE id = ?");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.bindLong(5, student.getId());
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select all data from the table
    public List getStudents() {
        List students = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city from student_info ORDER BY id ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Student std = new Student();
            std.setId(cursor.getInt(0));
            std.setName(cursor.getString(1));
            std.setAge(cursor.getInt(2));
            std.setClassName(cursor.getString(3));
            std.setCity(cursor.getString(4));
            students.add(std);
        }
        db.close();
        return students;
    }
    //Delete data from the table for the given id
    public void deleteData(int stdId){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM student_info WHERE id = ?");
        stmt.bindLong(1, stdId);
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select data for the given id
    public Student getStudentById(int stdId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city FROM student_info WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(stdId)});
        cursor.moveToFirst();
        Student std = new Student();
        std.setId(cursor.getInt(0));
        std.setName(cursor.getString(1));
        std.setAge(cursor.getInt(2));
        std.setClassName(cursor.getString(3));
        std.setCity(cursor.getString(4));
        db.close();
        return std;
    }
}
