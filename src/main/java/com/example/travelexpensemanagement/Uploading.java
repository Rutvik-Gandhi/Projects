package com.example.travelexpensemanagement;

public class Uploading {
    private String mname;
    private String mImageUrl;
    public Uploading()
    {}

    public Uploading(String name, String ImageUrl) {
        if(name.trim().equals(""))
        {name = "No Name"; }
        this.mname = name;
        this.mImageUrl = ImageUrl;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
