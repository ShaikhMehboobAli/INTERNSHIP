package com.example.coderspot;

public class user
{
    public String fname,lname,email,phno,dob,team,position;

    public user(String fname, String email, String phno,  String team, String position){
        //constructor for getng the value from db
        //TOBO
        this.fname = fname;

        this.email = email;
        this.phno = phno;
        this.team = team;
        this.position = position;
    }


}
