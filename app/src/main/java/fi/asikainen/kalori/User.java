package fi.asikainen.kalori;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

//(primaryKeys = {"nameFirst", "nameLast"})
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "user_age")
    public int age;
    @NonNull
    @ColumnInfo(name = "user_birthday")
    public Date birthday;
    //Possibly custom object 'Date' for birth date?
    @NonNull
    @ColumnInfo(name = "user_firstname")
    public String nameFirst;
    @NonNull
    @ColumnInfo(name = "user_lastname")
    public String nameLast;
    @NonNull
    @ColumnInfo(name = "user_gender")
    public String gender;

    public User(@NonNull int age, Date birthday, @NonNull String nameFirst, @NonNull String nameLast, @NonNull String gender) {
        //this.uid = id;
        this.age = age;
        this.birthday = birthday;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.gender = gender;
    }

    public int getId() { return id; }

    public int getAge() { return age; }

    public Date getBirthday() {return birthday;}

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getGender() {
        return gender;
    }
}
