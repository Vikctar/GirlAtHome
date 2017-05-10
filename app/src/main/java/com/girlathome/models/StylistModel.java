package com.girlathome.models;

import java.io.Serializable;

/**
 * Created by steve on 5/6/17.
 */

public class StylistModel
        implements Serializable {
    private String phone_number;

    private String status;

    private String email_verified;

    private String phone_code;

    private String account_status;

    private String stylist_id;

    private String email_code;

    private String phone_verified;

    private String day_end;

    private String day_start;

    private String updated_at;

    private String email;

    private String verified;

    private String name;

    private String created_at;

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getEmail_verified ()
    {
        return email_verified;
    }

    public void setEmail_verified (String email_verified)
    {
        this.email_verified = email_verified;
    }

    public String getPhone_code ()
    {
        return phone_code;
    }

    public void setPhone_code (String phone_code)
    {
        this.phone_code = phone_code;
    }

    public String getAccount_status ()
    {
        return account_status;
    }

    public void setAccount_status (String account_status)
    {
        this.account_status = account_status;
    }

    public String getStylist_id ()
    {
        return stylist_id;
    }

    public void setStylist_id (String stylist_id)
    {
        this.stylist_id = stylist_id;
    }

    public String getEmail_code ()
    {
        return email_code;
    }

    public void setEmail_code (String email_code)
    {
        this.email_code = email_code;
    }

    public String getPhone_verified ()
    {
        return phone_verified;
    }

    public void setPhone_verified (String phone_verified)
    {
        this.phone_verified = phone_verified;
    }

    public String getDay_end ()
    {
        return day_end;
    }

    public void setDay_end (String day_end)
    {
        this.day_end = day_end;
    }

    public String getDay_start ()
    {
        return day_start;
    }

    public void setDay_start (String day_start)
    {
        this.day_start = day_start;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getVerified ()
    {
        return verified;
    }

    public void setVerified (String verified)
    {
        this.verified = verified;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }
}
