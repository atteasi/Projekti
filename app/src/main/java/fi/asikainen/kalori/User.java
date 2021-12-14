package fi.asikainen.kalori;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Ricardo Nunes
 */
/**
 *Entity Class component of Room used to define Entity Objects that will populate single row in their table
 *and columns defined
 *User entity to store row ID, Insertion Date, User Age, User Birthday Date,
 *User First Name, User Last Name, User Gender
 */
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "creation_date")
    public Date insertDate;

    @NonNull
    @ColumnInfo(name = "user_age")
    public int age;

    @NonNull
    @ColumnInfo(name = "user_birthday")
    public Date birthday;

    @NonNull
    @ColumnInfo(name = "user_firstname")
    public String nameFirst;

    @NonNull
    @ColumnInfo(name = "user_lastname")
    public String nameLast;

    @NonNull
    @ColumnInfo(name = "user_gender")
    public String gender;

    /**
     *
     * @param insertDate Date type object of the current time
     * @param age int type User age
     * @param birthday Date type object of Birthday
     * @param nameFirst String type First name
     * @param nameLast String type Last name
     * @param gender String type Gender
     */
    public User(@NonNull Date insertDate, int age, Date birthday, @NonNull String nameFirst, @NonNull String nameLast, @NonNull String gender) {
        this.insertDate = insertDate;
        this.age = age;
        this.birthday = birthday;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.gender = gender;
    }

    public int getId() {return id;}

    public Date getInsertDate() {return insertDate;}

    public Date getBirthday() {return birthday;}

    public int getAge() {return age;}

    public String getNameFirst() {return nameFirst;}

    public String getNameLast() {return nameLast;}

    public String getGender() {return gender;}

}
